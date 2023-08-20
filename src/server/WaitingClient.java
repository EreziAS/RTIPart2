package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WaitingClient {

    private List<Socket> _socketList;
    private int _position;

    public WaitingClient()
    {
        this._socketList = new ArrayList<>();
        this._position = 0;
    }

    public synchronized void AddSocketToWaitingLine(Socket socket)
    {
        _socketList.add(socket);
        notify();
    }

    public synchronized Socket GetSocketFromWaitingLine() throws InterruptedException
    {
        wait();
        Socket socket = _socketList.get(_position);
        _position++;
        return socket;
    }
}
