package rit.cs;

/**
 * SubExpression uses the Expression interface in the cs package to interpret and evaluate expressions from the user that contain the subtraction operation
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

public class SubExpression implements Expression
{
    private Expression left;
    private Expression right;

    /**
     * Creates an expression with a left and right side with the operation of subtraction
     * @param left: left side of the expression
     * @param right: right side of the expression
     */
    public SubExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the expression by subtracting the left side from the right side
     * @return int: final value of the expression
     */
    public int evaluate()
    {
        return left.evaluate() - right.evaluate();
    }

    /**
     * Prints the expression as a string
     * @return String version of the expression
     */
    public String emit()
    {
        return "("+left.emit()+" - "+right.emit()+")";
    }
}
