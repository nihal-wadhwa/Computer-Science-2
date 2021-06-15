package client;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/** A networked version of the concentration game. This class represents the client side of an exchange between a client
 * and server.
 *
 * @author Nihal Wadhwa (nw7554)
 */

public class ConcentrationClient
{

    Socket ccSocket;
    PrintWriter out;
    BufferedReader in;
    Scanner userIn;
    String hostName;
    int portNumber;
    ConcentrationClientBoard cb;

    public ConcentrationClient(String hostName, int portNumber) throws IOException
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.ccSocket = new Socket(hostName, portNumber);
        this.out = new PrintWriter(ccSocket.getOutputStream(), true);
        this.in = new BufferedReader(
                new InputStreamReader(ccSocket.getInputStream()));
        this.userIn = new Scanner(System.in);
        int DIM = Integer.parseInt(in.readLine());
        this.cb = new ConcentrationClientBoard(DIM);

    }

    /**
     * Manages all input from server and executes the game from start to finish.
     */
    public void clientGame()
    {
        try
        {
            String input, output;
            boolean isDone = false;
            int counter = 0;
            while (!isDone)
            {
                System.out.println(cb);
                output = userIn.nextLine();
                out.println(output);
                input = in.readLine();
                System.out.println(input);

                String[] tokens = input.split(" ");
                if(tokens[0].equals("CARD"))
                {
                    counter++;
                    cb.revealCard(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),tokens[3]);
                    if(counter % 2 == 0)
                    {
                        input = in.readLine();
                        System.out.println(input);
                        tokens = input.split(" ");
                        if(tokens[0].equals("MATCH"))
                        {
                            cb.increaseMatch();
                            if(cb.getMatches() < ((cb.getDIM()*cb.getDIM())/2))
                            {
                            continue;
                            }
                            else
                            {
                            input = in.readLine();
                            System.out.println(input);
                            isDone = true;
                            }
                        }
                        else if (tokens[0].equals("MISMATCH"))
                        {

                            cb.hideCard(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
                            cb.hideCard(Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]));
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates the Concentration Client object which executes the game as well as handles the arguments.
     * @param args
     */
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Usage: java ConcentrationClient host port");
        }
        else
        {
            String hostNam = args[0];
            int portNum = Integer.parseInt(args[1]);
            ConcentrationClient client = null;
            try
            {
                client = new ConcentrationClient(hostNam, portNum);
                client.clientGame();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
