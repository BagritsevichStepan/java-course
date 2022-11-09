package expression.parser;

import expression.Expression;
import expression.ExpressionPriority;
import expression.exceptions.ParsingException;

public interface Parser {
    ExpressionPriority parse(String expression) throws ParsingException;
}
