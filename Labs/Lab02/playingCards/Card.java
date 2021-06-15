package playingCards;

public class Card
{
    private Rank rank;
    private Suit suit;


    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public int value()
    {
        return rank.getValue();
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public String toString()
    {
        return rank.toString() + " of " + suit.toString();
    }

    public String getShortName()
    {
        return rank.getShortName() + suit.getShortName();
    }


}
