/**
 * SieveOfEratosthenes uses a Sieve of Eratosthenes to determine whether or not a number is prime.
 *
 * @author Nihal Wadhwa, nw7554@rit.edu
 */



package sieve;

import java.util.Scanner;


public class SieveOfEratosthenes
{
    /**
     * Creates an array of all integers from 0 to upperBound that keeps track of whether each number is prime or no
     * @param upperBound: maximum number of integers in the sieve
     * @return sieve: array in which the index represents an integer and the value represents whether or not the index number is prime or not (0 for true and 1 for false)
     */
    public static int[] makeSieve(int upperBound)
    {
        int [] sieve = new int[upperBound];
        for (int i = 0; i < upperBound; i++)
        {
            if (i == 0 || i == 1)
            {
                sieve[i] = 1;
            }
            else
                {
                sieve[i] = 0;
            }

        }
        for (int i = 2; i < Math.sqrt((double)upperBound); i++)
        {
            if (sieve[i] == 0)
            {
                for(int j = (int) Math.pow(i,2); j < upperBound; j+=i)
                {
                    sieve[j] = 1;
                }
            }
        }
        return sieve;

    }

    /**
     * Main function that takes user input and checks that input to see whether that number is prime or not.
     * @param args: no return
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        boolean end = false;
        System.out.print("Please enter an upper bound: ");
        int bound = scan.nextInt();
        int[] sieve = makeSieve(bound);
        while (!end) {
            System.out.print("Please enter a positive number (0 to quit): ");
            int num = scan.nextInt();
            if (num == 0)
            {
                end = true;
            }
            else if (sieve[num] == 0)
            {
                System.out.println(num + " is prime!");
            }
            else
                {
                System.out.println(num + " is not prime.");
            }
        }
        System.out.println("Goodbye!");
    }

}
