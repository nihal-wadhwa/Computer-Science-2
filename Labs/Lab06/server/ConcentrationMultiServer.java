package server;

import common.ConcentrationException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcentrationMultiServer
{
    public static void main(String[] args) {
        if (args.length != 2)
        {
            System.out.println("Usage: java ConcentrationServer port DIM");
        }
        else
        {
            try (ServerSocket server = new ServerSocket(Integer.parseInt(args[0]))) {
                while (true) {
                    //Socket client = server.accept();
                    ConcentrationCSThread csThread = new ConcentrationCSThread(server.accept(), Integer.parseInt(args[1]));
                    csThread.start();
                }
            } catch (IOException | ConcentrationException e) {
                System.err.println("Could not listen on port " + Integer.parseInt(args[1]));
                System.exit(-1);
            }

        }

    }
}
