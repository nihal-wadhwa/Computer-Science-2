package miniPoker;

/**
 * A class that emulates the human hand in the mini poker game
 *
 * @author Nihal Wadhwa
 */

import playingCards.Card;
import java.util.Scanner;


public class Human
    {
        private Scanner in = new Scanner(System.in);
        public Card card1;
        public Card card2;

        /**
        * Creates a hand for the user
        *
        * @param in  scanner for taking user input for the game
        */
    public Human(Scanner in)
    {
        this.card1 = null;
        this.card2 = null;

    }

        /**
        * Adds card to the user's hand
        *
        * @param c Card to be added to hand
        */
    public void addCard(Card c)
    {
        if (card1 == null)
        {
            card1 = c;
        }
        else
            {
            card2 = c;
        }
    }

        /**
         * Prints out the user's hand
         */
    public void printHand()
    {
        System.out.println(card1);
        System.out.println(card2);
    }

        /**
         * Clears out  user's hand;
         */
    public void newHand()
    {
        card1 = null;
        card2 = null;
    }

        /**
         * Computes and returns the value of the user's hand
         * @return val: value of hand
         */
    public int value()
    {
        int val = card1.value() + card2.value();
        if (this.card1.getRank() == this.card2.getRank())
        {
            val += 10000;
        }
        else if(this.card1.getSuit() == this.card2.getSuit())
        {
            val += 5000;
        }
        return val;
    }

        /**
         *Asks the users if he wants to stand for the hand or not;
         * @return boolean value true for if the user wants to stand, and false for he doesn't want to.
         */
    public boolean stand()
    {
        System.out.print("Do you want to stand (y/n)? ");
        String stand = in.next();
        if (stand.equals("y") || stand.equals("Y"))
            return true;
        else
            return false;
    }

        /**
         * Compares user's hand to the computer's hand
         * @param computer computer's hand
         * @return -1 if the value of the user's hand les than that of the computer's, 0 if the values aare same, and 1 for everything else
         */
    public int compareTo(Computer computer)
    {
        if (this.value() < computer.value())
        {
            return -1;
        }
        else if (this.value() == computer.value())
        {
            return 0;
        }
        else
            return 1;
    }
}
