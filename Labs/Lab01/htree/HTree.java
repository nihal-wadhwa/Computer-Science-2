/**
 * Htree draws a tree of the letter H recursively.
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

package htree;

import static turtle.Turtle.*;

public class HTree {

    /** MAX_SEGMENT_LENGTH represents the max length possible for the turtle in this program. */
    public static final double MAX_SEGMENT_LENGTH = 200;


    /**
     * Initialize the turtle.
     * @param length: The length of one segment of the HTree.
     * @param depth: The depth of recursion.
     */
    public static void init(int length, int depth)
    {
        Turtle.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
        Turtle.title("H-Tree, depth: " + depth);
        Turtle.setPosition(0,0);
        Turtle.speed(0);
    }


    /**
     * Recursively draw the H-Tree.
     * @param length: the length of the current h-tree segments
     * @param depth: the current depth of recursion
     * Precondition: depth is greater than or equal to 0, turtle is at center of current h-tree, down, facing east
     * Postcondition: turtle is at center of current h-tree, down, facing east
     */
    public static void drawHTree(double length, int depth)
    {
        if(depth==0)
        {
            return;
        }
        else {
            Turtle.forward(length);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            drawHTree(length / 2, depth - 1);
            Turtle.left(90);
            Turtle.backward(length * 2);
            Turtle.right(90);
            drawHTree(length / 2, depth - 1);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.backward(length * 2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            drawHTree(length / 2, depth - 1);
            Turtle.left(90);
            Turtle.backward(length * 2);
            Turtle.right(90);
            drawHTree(length / 2, depth - 1);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward(length);
        }
    }


    /**
     * The main method reads the command line argument for the depth and draws the h-tree.
     * @param args The depth of recursion (an integer)
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.print("Usage: depth");
        }

        int depth = Integer.parseInt(args[0]);
        if (depth < 0) {
            System.out.println("The depth must be greater than or equal to 0");
        }

        init((int) MAX_SEGMENT_LENGTH, depth);
        drawHTree(MAX_SEGMENT_LENGTH, depth);


    }

}
