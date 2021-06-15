package rit.cs;

public class IntExpression implements Expression
{
    private int value;

    /**
     * Creates an integer expression
     * @param v integer value
     */
    public IntExpression(int v)
    {

        this.value = v;
    }

    /**
     * Evaluates the integer expression to itself
     * @return value of integer
     */
    public int evaluate()
    {
        return this.value;
    }

    /**
     * Evaluates the integer expression to itself
     * @return value of integer
     */
    public String emit()
    {
        return Integer.toString(value);
    }

}
