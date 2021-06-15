package server;
import common.ConcentrationException;
import game.ConcentrationBoard;
import game.ConcentrationCard;

import java.net.*;
import java.io.*;

import static common.ConcentrationProtocol.*;

/** A thread class that handles a single instance of the game with a client. Created so that the game can be played with
 *  multiple clients.
 *
 * @author Nihal Wadhwa (nw7554)
 */


public class ConcentrationCSThread extends Thread{

    int DIM;
    ConcentrationBoard board;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    /**
     * Declares all inputs and outputs and creates the board for the game after first getting the client socket.
     * Then the code calls the serverGame function.
     * @param DIM Dimensions of board
     * @param socket client socket
     * @throws IOException: handles IO exception from the ins/outs
     */
    public ConcentrationCSThread(Socket socket, int DIM) throws IOException, ConcentrationException {
        this.client = socket;
        this.DIM = DIM;
        InputStream inputStream = client.getInputStream();
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        this.in = new BufferedReader(inputReader);
        OutputStream outputStream = client.getOutputStream();
        this.out = new PrintWriter(outputStream, true);
        out.println(DIM);
        this.board = new ConcentrationBoard(this.DIM, false);

    }

    /**
     * Manages all input from client and executes the game from start to finish.
     */
    public void serverGame()
    {
        try
        {
            String input;
            while (!board.gameOver())
            {
                input = in.readLine();

                String[] fields = input.split(" ");
                String command = fields[0];
                int row = Integer.parseInt(fields[1]);
                int col = Integer.parseInt(fields[2]);
                if((fields.length != 3)|| !(fields[0].equals("REVEAL")))
                {
                    out.println("Error: invalid command");
                }
                else
                {

                    ConcentrationCard card = board.getCard(row, col);
                    // reveal the card and check if a match was made or not
                    out.println(String.format(CARD_MSG, row, col, card.getLetter()));
                    ConcentrationBoard.CardMatch match = board.reveal(row, col);
                    // if the match is not ready this was the first of two cards revealed
                    if (match.isReady())
                    {
                        if (match.isMatch())
                        {
                            // if there is a match show the server to client match message
                            out.println(String.format(MATCH_MSG,
                                    match.getCard1().getRow(), match.getCard1().getCol(),
                                    match.getCard2().getRow(), match.getCard2().getCol()));
                        }
                        else {
                            // if there is not a match show the server to client mismatch message
                            out.println(String.format(MISMATCH_MSG,
                                    match.getCard1().getRow(), match.getCard1().getCol(),
                                    match.getCard2().getRow(), match.getCard2().getCol()));
                        }
                    }
                }
            }
            out.println(GAME_OVER_MSG);
        }
        catch(IOException | ConcentrationException e)
        {
            e.printStackTrace();
        }

    }



    public void run() {
        serverGame();
    }
}
