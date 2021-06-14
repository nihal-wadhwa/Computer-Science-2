/**
 * GoodHashFunc computes a hash from a given string.
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */

package hashing;
import java.util.Scanner;


public class GoodHashFunc {

    /**
     * computeHash takes in a string input and creates a valid hash with it.
     * @param input: string being used to create hash
     * @return sum: resulting hash
     */
    public static int computeHash(String input)
    {
        int [] arr = new int[input.length()];
        int n = input.length();

        for(int i = 0; i < input.length(); i++)
        {
            arr[i] = input.charAt(i)*(int)Math.pow(31, n-(i+1));
        }

        int sum = 0;
        int x = 0;
        while(x < arr.length)
        {
            sum += arr[x];
            x++;
        }
        return sum;
    }


    /**
     * Main function that takes user input as a string and converts that input into a hash.
     * @param args: no return
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.print("Enter a string: ");
        String input = scan.next();
        System.out.print("The computed hash for the specified string is: " + computeHash(input));
    }
}
