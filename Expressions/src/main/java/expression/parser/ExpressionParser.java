package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExpressionParser extends BaseParser {
    private BackoffSource lostSource = new BackoffSource("");
    private static final int MAX_DEPTH = Operator.getMaxPriority();
    private static final List<String> VARIABLE_NAMES = List.of("x", "y", "z");

    @Override
    protected void nextChar() {
        if (lostSource.hasNext()) {
            curChar = lostSource.getNext();
        } else {
            if (!lostSource.isEmpty()) {
                lostSource = new BackoffSource("");
            }
            curChar = (source.hasNext()) ? source.getNext() : EOF;
        }
    }

    @Override
    protected int getCurPos() {
        return source.getPos() - (lostSource.getSourceLength() - lostSource.getPos());
    }

    @Override
    public ExpressionPriority parse(String expression) throws ParsingException {
        setSource(new StringSource(expression));
        return parseExpression(0);
    }

    private ExpressionPriority parseExpression(int curDepth) throws ParsingException {
        skipWhitespaces();

        if (curDepth == MAX_DEPTH) {
            return parseValue();
        }

        ExpressionPriority curExpression = parseExpression(curDepth + 1);
        while (true) {
            Operator binaryOperator;
            try {
                binaryOperator = parseBinaryOperator();
            } catch (UndefinedBinaryOperatorException e) {
                break;
            }

            if (curDepth != getOperatorDepth(binaryOperator)) {
                returnSource(Operator.getOperatorSign(binaryOperator));
                break;
            }

            curExpression = buildBinaryExpression(
                    curExpression,
                    binaryOperator,
                    parseExpression(curDepth + 1)
            );
        }
        return curExpression;
    }

    private ExpressionPriority parseValue() throws ParsingException {
        skipWhitespaces();

        if (test('(')) {
            ExpressionPriority result = parseExpression(0);
            skipWhitespaces();
            if (!test(')')) {
                throw new MissingBracketException(")", getCurPos());
            }
            return result;
        }

        try {
            Operator unaryOperator = parseUnaryOperator();
            if (unaryOperator == Operator.NEGATE && isDigit(curChar)) {
                return parseConst(true);
            }
            return buildUnaryExpression(unaryOperator, parseValue());
        } catch (UndefinedUnaryOperatorException e) {
            skipWhitespaces();
            return isDigit(curChar) ? parseConst(false) : parseVariable();
        }
    }

    private Variable parseVariable() throws InvalidVariableException {
        skipWhitespaces();
        String variable = parseString(Character::isLetter);
        if (!VARIABLE_NAMES.contains(variable)) {
            throw new InvalidVariableException(variable, getCurPos());
        }
        return new Variable(variable);
    }

    private Const parseConst(boolean isNegative) throws IllegalConstantException {
        skipWhitespaces();
        String number = (isNegative ? '-' : '+') + parseString(
                c -> (Character.isDigit(c) || c == '.')
        );
        try {
            return new Const(Integer.parseInt(number));
        } catch (NumberFormatException e1) {
            try {
                return new Const(new BigDecimal(number));
            } catch (NumberFormatException e2) {
                throw new IllegalConstantException(number, getCurPos());
            }
        }
    }

    private String parseString(Predicate<Character> isStringChar) {
        skipWhitespaces();

        StringBuilder sb = new StringBuilder();
        while (isStringChar.test(curChar)) {
            sb.append(curChar);
            nextChar();
        }
        return sb.toString();
    }

    private Operator parseBinaryOperator() throws UndefinedBinaryOperatorException {
        skipWhitespaces();
        try {
            return parseOperator(Operator.BINARY_OPERATORS, Operator::getBinaryOperator);
        } catch (UndefinedOperatorException e) {
            throw new UndefinedBinaryOperatorException(Character.toString(curChar), getCurPos());
        }
    }

    private Operator parseUnaryOperator() throws UndefinedUnaryOperatorException {
        skipWhitespaces();
        try {
            return parseOperator(Operator.UNARY_OPERATORS, Operator::getUnaryOperator);
        } catch (UndefinedOperatorException e) {
            throw new UndefinedUnaryOperatorException(Character.toString(curChar), getCurPos());
        }
    }

    private Operator parseOperator(String[] operators, Function<String, Operator> getOperator)
            throws UndefinedOperatorException {
        skipWhitespaces();
        for (String expectedOperator : operators) {
            int entryPrefLength = maxEntryPref(expectedOperator);
            if (entryPrefLength == expectedOperator.length()) {
                return getOperator.apply(expectedOperator);
            }
            returnSource(expectedOperator.substring(0, entryPrefLength));
        }

        throw new UndefinedOperatorException(Character.toString(curChar), getCurPos());
    }

    private int maxEntryPref(String s) {
        int pref = 0;
        for (char c : s.toCharArray()) {
            if (!test(c)) {
                break;
            }
            pref++;
        }
        return pref;
    }

    private void returnSource(String readString) {
        if (!lostSource.isEmpty()) {
            lostSource.returnSourceSuf(readString.length() + 1);
        } else {
            lostSource = new BackoffSource(readString + curChar);
        }
        nextChar();
    }

    private UnaryOperation buildUnaryExpression(Operator operator, ExpressionPriority expression) {
        switch (operator) {
            case NEGATE: return new CheckedNegate(expression);
            case L0: return new NumberOfLeadingZeros(expression);
            case T0: return new NumberOfTrailingZeros(expression);
            case ABS: return new CheckedAbs(expression);
        }

        throw new UnsupportedOperationException("Unsupported unary operation " + operator);
    }

    private BinaryOperation buildBinaryExpression(ExpressionPriority left, Operator operator, ExpressionPriority right) {
        switch (operator) {
            case POW: return new CheckedPow(left, right);
            case LOG: return new CheckedLog(left, right);
            case MULTIPLY: return new CheckedMultiply(left, right);
            case DIVIDE: return new CheckedDivide(left, right);
            case ADD: return new CheckedAdd(left, right);
            case SUBTRACT: return new CheckedSubtract(left, right);
            case LEFT_SHIFT: return new LeftShift(left, right);
            case RIGHT_SHIFT: return new RightShift(left, right);
            case UNSIGNED_RIGHT_SHIFT: return new UnsignedRightShift(left, right);
        }

        throw new UnsupportedOperationException("Unsupported binary operation " + operator);
    }

    private static int getOperatorDepth(Operator operator) {
        return MAX_DEPTH - Operator.getOperatorPriority(operator);
    }
}
