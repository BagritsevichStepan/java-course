package expression;

import java.util.*;
import java.util.stream.Collectors;

public enum Operator {
    CONST, VARIABLE,
    NEGATE, L0, T0, ABS,
    POW, LOG,
    MULTIPLY, DIVIDE,
    ADD, SUBTRACT,
    LEFT_SHIFT, RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT;

    private static final List<Set<Operator>> OPERATOR_PRIORITY = List.of(
            EnumSet.of(CONST, VARIABLE),
            EnumSet.of(NEGATE, L0, T0, ABS),
            EnumSet.of(POW, LOG),
            EnumSet.of(MULTIPLY, DIVIDE),
            EnumSet.of(ADD, SUBTRACT),
            EnumSet.of(LEFT_SHIFT, RIGHT_SHIFT, UNSIGNED_RIGHT_SHIFT)
    );

    private static final Map<String, Operator> BINARY_OPERATOR_TO_ENUM = Map.of(
            "**", POW,
            "//", LOG,
            "*", MULTIPLY,
            "/", DIVIDE,
            "+", ADD,
            "-", SUBTRACT,
            "<<", LEFT_SHIFT,
            ">>>", UNSIGNED_RIGHT_SHIFT,
            ">>", RIGHT_SHIFT
    );

    private static final Map<String, Operator> UNARY_OPERATOR_TO_ENUM = Map.of(
            "-", NEGATE,
            "l0", L0,
            "t0", T0,
            "abs", ABS
    );

    private static final EnumMap<Operator, String> ENUM_TO_OPERATOR;

    public static final String[] BINARY_OPERATORS;

    public static final String[] UNARY_OPERATORS;

    static {
        ENUM_TO_OPERATOR = new EnumMap<>(Operator.class);
        ENUM_TO_OPERATOR.putAll(
                BINARY_OPERATOR_TO_ENUM.entrySet().stream().collect(
                        Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
                )
        );
        ENUM_TO_OPERATOR.putAll(
                UNARY_OPERATOR_TO_ENUM.entrySet().stream().collect(
                        Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
                )
        );

        BINARY_OPERATORS = BINARY_OPERATOR_TO_ENUM.keySet().toArray(new String[0]);
        Arrays.sort(BINARY_OPERATORS,
                (String a, String b) -> -1 * Integer.compare(a.length(), b.length())
        );

        UNARY_OPERATORS = UNARY_OPERATOR_TO_ENUM.keySet().toArray(new String[0]);
        Arrays.sort(UNARY_OPERATORS,
                (String a, String b) -> -1 * Integer.compare(a.length(), b.length())
        );
    }

    private static Operator getOperator(String operatorSign, Map<String, Operator> operatorToEnum) {
        Operator operator = operatorToEnum.get(operatorSign);
        if (operator != null) {
            return operator;
        }

        throw new UnsupportedOperationException("Operator " + operatorSign + " is unknown");
    }

    public static Operator getBinaryOperator(String operatorSign) {
        return getOperator(operatorSign, BINARY_OPERATOR_TO_ENUM);
    }

    public static Operator getUnaryOperator(String operatorSign) {
        return getOperator(operatorSign, UNARY_OPERATOR_TO_ENUM);
    }

    public static String getOperatorSign(Operator operator) {
        String operatorSign = ENUM_TO_OPERATOR.get(operator);
        if (operatorSign != null) {
            return operatorSign;
        }

        throw new UnsupportedOperationException("Operator Sign of the Operator " + operator + " is unknown");
    }

    public static int getOperatorPriority(Operator operator) {
        int priority = 0;
        for (Set<Operator> curPriorityOperators : OPERATOR_PRIORITY) {
            if (curPriorityOperators.contains(operator)) {
                return priority;
            }
            priority++;
        }

        throw new UnsupportedOperationException("Priority of the Operator " + operator + " is unknown");
    }

    public static int getMaxPriority() {
        return OPERATOR_PRIORITY.size() - 1;
    }
}
