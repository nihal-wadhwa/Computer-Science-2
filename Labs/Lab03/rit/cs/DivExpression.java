package rit.cs;

/**
 * DivExpression uses the Expression interface in the cs package to interpret and evaluate expressions from the user that contain the division operation
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

public class DivExpression implements Expression
{
    private Expression left;
    private Expression right;

    /**
     * Creates an expression with a left and right side with the operation of division
     * @param left: left side of the expression
     * @param right: right side of the expression
     */
    public DivExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the expression by dividing the left side from the right side
     * @return int: final value of the expression
     */
    public int evaluate()
    {
        return left.evaluate() / right.evaluate();
    }

    /**
     * Prints the expression as a string
     * @return String version of the expression
     */
    public String emit()
    {
        return "("+left.emit()+" / "+right.emit()+")";
    }
}
