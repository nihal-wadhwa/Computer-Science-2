/**
 * PrimalityTest checks whether a number is prime or not.
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */


package primality;

import java.util.Scanner;

public class PrimalityTest {

    /**
     * isPrime takes a number and checks whether it is prime or not
     * @param number: number checked if it is prime or not
     * @return boolean type: true if number is prime, false if it is not
     */
    public static boolean isPrime(int number) {
        if(number >= 0 && number < 2)
        {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main function used to take in user input and checks if that number is prime or not.
     * @param args: no return
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        boolean end = false;
        while (!end) {
            System.out.print("Please enter a positive number (0 to quit): ");
            int num = scan.nextInt();
            if (num == 0) {
                end = true;
            }
            else if (isPrime(num)) {
                System.out.println(num + " is prime!");
            }
            else {
                System.out.println(num + " is not prime.");
            }
        }
        System.out.println("Goodbye!");
        }
    }
