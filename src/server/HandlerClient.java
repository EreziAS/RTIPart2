package server;

import db.bean.ActivityBean;
import db.bean.ReservationBean;
import protocol.FUCAMP;
import protocol.ROMP;

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
    int _port;

    public HandlerClient(WaitingClient waitingClient, int port)
    {
        this._waitingClient = waitingClient;
        this._port = port;
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

                switch (_port)
                {
                    case 50017:
                        //FUCAMP
                        ActivityBean bean = new ActivityBean();
                        bean.Connect("rti2","root","Rotko3");
                        System.out.println("HandlerClient "+this.getId()+" : FUCAMP");
                        FUCAMP fucamp = new FUCAMP();
                        fucamp.Processing(bean, receive, send);
                        break;
                    case 50018:
                        //ROMP
                        System.out.println("HandlerClient "+this.getId()+" : ROMP");
                        ReservationBean bean2 = new ReservationBean();
                        bean2.Connect("rti2","root","Rotko3");
                        ROMP romp = new ROMP();
                        romp.Processing(bean2, receive, send);
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
