# Expressions
The project parses mathematical expressions and stores them as classes, is able to compare and display expressions in different variants, can evaluate expressions with several variables. 

[Examples](#examples)

## Problem statement
1. [First part. Expressions calculation](#first-part)
2. [Second part. Expressions parser](#second-part)
3. [Third part. Expressions exceptions. Checked mode for the expression calculations](#third-part)

### First part
1. Implement following classes to evaluate expressions:
   + `Const` implements a constant value
   + `Variable` implements a variable $x$, $y$ or $z$
   + `Add` implements addition operation. Operator sign: `+`
   + `Subtract` implements subtraction operation. Operator sign: `-`
   + `Multiply` implements multiplication operation. Operator sign: `*`
   + `Divide` implements division operation. Operator sign: `/`
   + `Pow` implements power operation, evaluates the value of the left expression raised to the power of the value of the right expression. Operator sign: `**`
   + `Log` implements logarithmic operation, evaluates the logarithm (base is the value of the right expression) of the value of the left expression. Operator sign: `//`
   + `RightShift` implements right shift operation. Operator sign: `>>`
   + `UnsignedRightShift` implements unsigned right shift operation. Operator sign: `>>>`
   + `LeftShift` implements left shift operation. Operator sign: `<<`
   + `Negate` implements negation operation, changes the sign (multiplies by -1) of the expression value. Operator sign: `-`
   + `Abs` implements operation with absolute value. Operator sign: `abs`
   + `NumberOfLeadingZeros` evaluates the number of zero bits preceding the highest-order ("leftmost") one-bit in the two's complement binary representation of the expression value. Operator sign: `l0`
   + `NumberOfTrailingZeros` evaluates the number of zero bits following the lowest-order ("rightmost") one-bit in the two's complement binary representation of the expression value. Operator sign: `t0`

2. Classes must allow the following expressions:
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).evaluate(5)
```
When such an expression is evaluated, the value passed to the method `evaluate` as a parameter is substituted for each variable. Thus, the result of the calculation of the above example should be number 7.
   
3. The method `toString` must display an expression in the full-brackets-form. For example
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).toString()
```
must return $((2 * x) - 3)$.

4. The method `toMiniString` (interface `ToMiniString`) must display an expression with the minimum number of brackets. For example
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).toMiniString()
```
must return $2 * x - 3$. 

5. Implement method `equals` that proves if two expressions are equal. For example
```
new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Const(2), new Variable("x")))
```
must return `true` and
```
new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Variable("x"), new Const(2)))
```
must return `false`.

6. Priorities of the operations, starting from the highest:
   1. `Negate`, `Abs`, `NumberOfLeadingZeros`, `NumberOfTrailingZeros`
   2. `Pow`, `Log`
   3. `Multiply`, `Divide`
   4. `Add`, `Subtract`
   5. `RightShift`, `UnsignedRightShift`, `LeftShift` 
7. Interface `Expression` must contain the method `evaluate` to evaluate expression in type int with one variable
8. Interface `BigDecimalExpression` must contain the method `evaluate` to evaluate an expression in type BigDecimal with one variable
9. Interface `TripleExpression` must contain the method `evaluate` to evaluate an expression in type int with three variables
10. When performing the task, you should pay attention to:
      + Creating a common interface for the classes
      + Creating an abstract base class for binary and unary operations

### Second part
1. Add the implementation of expression parser (interface `Parser` with method `parse`), so that the expression is built on the string like:
```
x * (x - 2)*x + 1
```
2. Expression parser must be in the package `parser`
3. Expression string can contain:
   + all binary and unary operations
   + constants in `int` or `BigDecimal`
   + variables $x$, $y$ or $z$
   + round brackets to explicitly denote the priority of operations
   + any number of whitespace characters in any place
4. It is recommended to parse expressions using the [recursive descent method](https://en.wikipedia.org/wiki/Recursive_descent_parser). The algorithm must work in linear time


### Third part
1. Add the implementation of exceptions thrown during the parsing or evaluation of expressions
2. Exceptions must be in the package `exceptions`
3. The following exceptions must be implemented:
   + Overflows
   + Division by zero
   + Missing brackets in expressions
   + Invalid variables in expressions
   + Undefinded operators in expressions
   + Illegal constants in expressions
   + Invalid arguments in operations (`Pow`, `Log`)
4. Add checked mode for the expression calculations using implemented exceptions. The following operations must have checked mode:
   + `CheckedAdd`, `CheckedSubtract`
   + `CheckedMultiply`, `CheckedDivide`
   + `CheckedPow`, `CheckedLog`
   + `CheckedNegate`, `CheckedAbs`
5. You __cannot__ use types `long` and `double` for checked mode
5. You __cannot__ use methods of the classes `Math` and `StrictMath` for checked mode

## Examples
Run `Example.java` with three arguments: value for $x$, value for $y$, value for $z$

As an example, three expressions will be parsed, printed and then evaluated:
1. Expression `-x*l08+(4*-(6))` with `int x` (first argument). Checked mode is on.
2. BigDecimal expression `-(3.86)+(4.5*x)` with `BigDecimal x` (first argument). Checked mode is off.
3. Triple expression `(-3*(x))+y*z+t04` with `int x`, `int y` and `int z` (three arguments). Checked mode is on.

