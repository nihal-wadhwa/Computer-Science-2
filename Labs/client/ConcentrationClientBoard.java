package client;
import common.ConcentrationException;
import game.ConcentrationCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.ConcentrationCard.HIDDEN;

/**
 *
 */

public class ConcentrationClientBoard {

    private String HIDDEN = ".";
    private int matches;
    private int DIM;
    private String board[][];
    private ConcentrationCard revealedCard;


    public ConcentrationClientBoard(int DIM)
    {

        this.DIM = DIM;
        this.board = new String[DIM][DIM];
        for (int row=0; row<DIM; ++row)
        {
            for (int col=0; col<DIM; ++col)
            {
                this.board[row][col] = HIDDEN;
            }
        }

    }

    public int getDIM() {
        return DIM;
    }

    public int getMatches() {
        return matches;
    }

    public void increaseMatch()
    {
        this.matches++;
    }


    public void hideCard(int row, int col)
    {
        try
        {
            if((row < 0 || row > DIM) || (col < 0 || col > DIM))
            {
                throw new ConcentrationException("invalid coordinate");
            }
            else
            {
                this.board[row][col] = HIDDEN;
            }
        }
        catch (ConcentrationException e)
        {
            e.printStackTrace();
        }

    }

    public void revealCard(int row, int col, String val)
    {
        try
        {
            if((row < 0 || row > DIM) || (col < 0 || col > DIM))
            {
                throw new ConcentrationException("invalid coordinate");
            }
            else
            {
                this.board[row][col] = val;
            }
        }
        catch (ConcentrationException e)
        {
            e.printStackTrace();
        }
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        // build the top row of indices
        str.append("  ");
        for (int col=0; col<this.DIM; ++col)
        {
            str.append(col);
        }
        str.append("\n");
        // build each row of the actual board
        for (int row=0; row<this.DIM; ++row) {
            str.append(row).append("|");
            // build the columns of the board
            for (int col=0; col<this.DIM; ++col) {
                str.append(this.board[row][col]);
            }
            str.append("\n");
        }
        return str.toString();
    }

}
