package rit.stu;

import rit.cs.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * DYI uses the other Expression classes in the cs package to interpret and evaluate expressions from the user.
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

public class DYI {

    private ArrayList<String> inp;

    /**
     * Creates a DYI object that takes in a raw expression and outputs the transformed expression as well as its answer.
     * @param inputExpress: user input expression
     */
    public DYI(String inputExpress) {
        this.inp = new ArrayList<>(Arrays.asList(inputExpress.split(" ")));
        Expression math = parse(this.inp);
        System.out.println("Emit: " + math.emit());
        System.out.println("Evaluate: " + math.evaluate());
    }

    /**
     * This function determines the kind of expression given by the user
     * @param token user input
     * @return Expression: type of expression
     */
    public Expression parse(ArrayList<String> token)
    {
        String t = token.remove(0);
        if (t.equals("+"))
        {
            return new AddExpression(parse(token), parse(token));
        }
        else if (t.equals("-"))
        {
            return new SubExpression(parse(token), parse(token));
        }
        else if (t.equals("/"))
        {
            return new DivExpression(parse(token), parse(token));
        }
        else if (t.equals("%"))
        {
            return new ModExpression(parse(token), parse(token));
        }
        else if (t.equals("*"))
        {
            return new MulExpression(parse(token), parse(token));
        }
        else {
            return new IntExpression(Integer.parseInt(t));
        }
    }

    /**
     * Runs Derp the Interpreter scans in the user input
     *
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Derp your interpreter :]");
        boolean run = true;
        while(run)
        {
            System.out.print("> ");
            String inpExpress = scan.nextLine();
            if(inpExpress.equals("quit"))
            {
                run = false;
            }
            else
            {
                new DYI(inpExpress);
            }
        }
    }
}
