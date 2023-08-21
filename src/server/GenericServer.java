package server;

import java.io.IOException;
import java.net.ServerSocket;

public class GenericServer extends Thread{
    private ServerSocket _serverSocket;
    private int _port;

    private WaitingClient _waitingClient;

    public GenericServer(int port)
    {
        _port = port;
        this._waitingClient = new WaitingClient();



        switch (port)
        {
            case 50017:
                System.out.println("Server FUCAMP started");
                break;
            case 50018:
                System.out.println("Server ROMP started");
                break;
            default:
                System.out.println("Server started on port: " + port);
                break;
        }
    }
    @Override
    public void run()
    {
        System.out.println("Server running");

        try
        {
            _serverSocket = new ServerSocket(this._port);
            LaunchClientThreads();
        }catch (IOException e)
        {
            System.out.println("Error SocketServer: " + e);
        }

        while(!this.isInterrupted())
        {
            try{
                _waitingClient.AddSocketToWaitingLine(_serverSocket.accept());
            }catch (IOException e) {
                System.out.println("Error SocketServer: " + e);
            }
        }

    }

    private void LaunchClientThreads()
    {
        for(int i = 0; i < 5; i++)
        {
            HandlerClient threadClient = new HandlerClient(_waitingClient,_port);
            threadClient.start();
        }
    }

    synchronized public void StopServer () throws IOException
    {
        System.out.println("Server stopped");
        _serverSocket.close();
    }

}
