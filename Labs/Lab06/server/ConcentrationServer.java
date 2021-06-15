package server;
import common.ConcentrationException;
import game.ConcentrationBoard;
import game.ConcentrationCard;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

import static common.ConcentrationProtocol.*;

/** A networked version of the concentration game. This class represents the server side of an exchange between a SINGLE
 * CLIENT.
 *
 * @author Nihal Wadhwa (nw7554)
 */



public class ConcentrationServer
{
    ConcentrationBoard board;
    ServerSocket server;
    Socket client;
    BufferedReader in;
    PrintWriter out;
    int portNumber;

    /**
     * Declares all inputs and outputs and creates the board for the game after first getting the client socket.
     * Then the code calls the serverGame function.
     * @param portNum port number of the client socket
     * @param DIM Dimensions of board
     * @param board actual representation of Concentration board being used.
     * @throws IOException: handles IO exception from the ins/outs
     */
    public ConcentrationServer(int portNum, int DIM, ConcentrationBoard board) throws IOException {
        this.portNumber = portNum;
        this.server = new ServerSocket(portNum);
        this.client = server.accept();
        InputStream inputStream = client.getInputStream();
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        this.in = new BufferedReader(inputReader);
        OutputStream outputStream = client.getOutputStream();
        this.out = new PrintWriter(outputStream, true);
        out.println(DIM);
        this.board = board;
        serverGame();
    }

    /**
     * Manages all input from client and executes the game from start to finish.
     */
    public void serverGame()
    {
        try
        {
            String input, output;
            ConcentrationCard card1= null, card2 = null;
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
                    System.out.print("Hello");
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

    /**
     * Creates the Concentration Server object which executes the game as well as handles the arguments.
     * @param args
     */
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Usage: java ConcentrationServer port DIM");
        }
        else
        {
            ServerSocket server = null;
            int portNum = Integer.parseInt(args[0]);
            int DIM = Integer.parseInt(args[1]);
            ConcentrationServer cServer = null;
            try
            {
                ConcentrationBoard board = new ConcentrationBoard(Integer.parseInt(args[1]), false);
                cServer = new ConcentrationServer(portNum, DIM, board);
                server.close();
            }
            catch (ConcentrationException | InputMismatchException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
