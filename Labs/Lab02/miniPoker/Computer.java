package miniPoker;

/**
 * A class that emulates the computer hand in the mini poker game.
 *
 * @author Nihal Wadhwa
 */

import playingCards.Card;

public class Computer
{

    public Card card1;
    public Card card2;

    /**
     * Creates a hand for the computer
     */
    public Computer()
    {
        this.card1 = null;
        this.card2 = null;

    }

    /**
     * Adds card to the computer's hand
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
        if(card1.getRank() == card2.getRank())
        {
            return true;
        }
        else if(card1.getSuit() == card2.getSuit())
        {
            return true;
        }
        else if((card1.getRank().equals("KING")) || (card2.getRank().equals("KING")) )
        {
            return true;
        }
        else if((card1.getRank().equals("ACE")) || (card2.getRank().equals("ACE")) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
