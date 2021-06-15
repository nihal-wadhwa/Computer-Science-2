package rit.cs;

/**
 * ModExpression uses the Expression interface in the cs package to interpret and evaluate expressions from the user that contain the modulus operation
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

public class ModExpression implements Expression
{
    private Expression left;
    private Expression right;

    /**
     * Creates an expression that contains the modulus operation
     * @param left: left side of expression
     * @param right right side of expression
     */
    public ModExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the expression by doing the modulus operation between the left side and the right side
     * @return int: final value of the expression
     */
    public int evaluate()
    {
        return left.evaluate() % right.evaluate();
    }

    /**
     * Prints the expression as a string
     * @return String version of the expression
     */
    public String emit()
    {
        return "("+left.emit()+" % "+right.emit()+")";
    }
}
