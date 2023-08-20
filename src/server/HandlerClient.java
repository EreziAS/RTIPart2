package server;

import db.bean.ActivityBean;
import protocol.FUCAMP;

import java.io.*;
import java.net.*;

/**
 * 1. Wait for a client to connect
 * 2. Create stream to read and write from/to the client
 * 3. Read the first packet from the client to specify the protocol
 * 4. Process all the requests needed
 */



public class HandlerClient extends Thread{

    private WaitingClient _waitingClient;
    private Socket _clientSocket;

    public HandlerClient(WaitingClient waitingClient)
    {
        this._waitingClient = waitingClient;
    }

    public void run()
    {
        while(!this.isInterrupted())
        {
            System.out.println("HandlerClient "+this.getId()+" : Waiting for client...");
            try
            {
                _clientSocket =_waitingClient.GetSocketFromWaitingLine();
                System.out.println("HandlerClient "+this.getId()+" : Client connected");
            }catch (InterruptedException e)
            {
                System.out.println("HandlerClient "+this.getId()+" : Error HandlerClient: " + e);
            }

            try
            {
                //Create the streams
                BufferedReader receive = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
                PrintWriter send = new PrintWriter(_clientSocket.getOutputStream(), true);

                //Read the request
                String line = receive.readLine();
                System.out.println("HandlerClient "+this.getId()+" : Request received: " + line);
                String request[] = line.split(";");
                System.out.println("HandlerClient "+this.getId()+" : Request splitted: " + request[0] + " " + request[1] + " " + request[2]);

                //Specify the protocol

                switch (request[1])
                {
                    case "FUCAMP":
                        ActivityBean bean = new ActivityBean();
                        bean.Connect("rti2","root","Rotko3");

                        System.out.println("HandlerClient "+this.getId()+" : FUCAMP");
                        FUCAMP fucamp = new FUCAMP();
                        fucamp.Processing(line, bean, receive, send);
                        break;
                    default:
                        System.out.println("HandlerClient "+this.getId()+" : Protocol not found");
                        break;
                }







            }catch (Exception e)
            {
                System.out.println("HandlerClient "+this.getId()+" : Error HandlerClient: " + e);
            }
        }
    }




}
