package rit.cs;

/**
 * MulExpression uses the Expression interface in the cs package to interpret and evaluate expressions from the user that contain the multiplication operation
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

public class MulExpression implements Expression
{
    private Expression left;
    private Expression right;

    /**
     * Creates an expression with a left and right side with the operation of multiplication
     * @param left: left side of the expression
     * @param right: right side of the expression
     */
    public MulExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the expression by multiplying the left side from the right side
     * @return int: final value of the expression
     */
    public int evaluate()
    {
        return left.evaluate() * right.evaluate();
    }

    /**
     * Prints the expression as a string
     * @return String version of the expression
     */
    public String emit()
    {
        return "("+left.emit()+" * "+right.emit()+")";
    }
}
