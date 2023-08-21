package protocol;

import com.sun.tools.jconsole.JConsoleContext;
import db.bean.ReservationBean;
import db.bean.ReservationLoginBean;
import db.bean.RoomBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

// ROMP PORT 50018

public class ROMP {

    private int _quit = 0;
    private boolean _isLogged = false;

    private String request = null;

    public ROMP() {

    }

    public void Processing(ReservationBean bean, BufferedReader receive, PrintWriter send) throws IOException, SQLException
    {
        while(_quit==0)
        {
            if(request==null)
            {
                request = receive.readLine();
            }
            String[] requestArray = request.split(";");

            switch (requestArray[2])
            {
                case "LOGIN":
                    //LOGIN
                    System.out.println("COMMAND LOGIN");

                    ReservationLoginBean loginBean = new ReservationLoginBean();
                    loginBean.Connect("rti2","root","Rotko3");
                    if(loginBean.Login(requestArray[3],requestArray[4]))
                    {
                        _isLogged = true;
                        send.println("00;ROMP;LOGIN;Login success");
                    }
                    else
                    {
                        _isLogged = false;
                        send.println("00;ROMP;LOGIN;Login failed");
                    }
                    break;
                case "LOGOUT":
                    //LOGOUT
                    System.out.println("COMMAND LOGOUT");
                    _isLogged = false;
                    break;
                case "BROOM":
                    //BOOK ROOM
                    System.out.println("COMMAND BROOM");
                    if(_isLogged)
                    {
                        int temp = bean.makeReservation(Integer.parseInt(requestArray[3]),requestArray[4]);
                        if(temp==1)
                        {
                            send.println("00;ROMP;BROOM;Reservation success");
                        }
                        else if(temp==2)
                        {
                            send.println("00;ROMP;BROOM;Error BROOM : Room already booked");
                        }
                    }
                    else {
                        send.println("00;ROMP;LOGIN;You are not logged");
                    }
                    break;
                case "PROOM":
                    //PAY ROOM
                    System.out.println("COMMAND PROOM");
                    if(_isLogged)
                    {
                        if(bean.payReservation(Integer.parseInt(requestArray[3]), requestArray[4])==1)
                        {
                            send.println("00;ROMP;PROOM;Payment success");
                        }
                        else
                        {
                            send.println("00;ROMP;PROOM;Payment failed");
                        }
                    }
                    else {
                        send.println("00;ROMP;LOGIN;You are not logged");
                    }
                    break;
                case "CROOM":
                    //CANCEL ROOM
                    System.out.println("COMMAND CROOM");
                    if(_isLogged)
                    {
                        if(bean.cancelReservation(Integer.parseInt(requestArray[3]), requestArray[4])==1)
                        {
                            send.println("00;ROMP;CROOM;Cancellation success");
                        }
                        else
                        {
                            send.println("00;ROMP;CROOM;Cancellation failed");
                        }
                    }
                    else {
                        send.println("00;ROMP;LOGIN;You are not logged");
                    }
                    break;
                case "LROOM":
                    //LIST ROOM
                    System.out.println("COMMAND LROOM");
                    if(_isLogged)
                    {
                        RoomBean roomBean = new RoomBean();
                        roomBean.Connect("rti2","root","Rotko3");
                        ResultSet result = roomBean.getAll();
                        while (result.next())
                        {
                            send.println("00;ROMP;LROOM;"+result.getString("id")+";"+result.getString("category")+";"+result.getString("occupancy")+";"+result.getString("price")+";"+result.getString("busy"));
                        }
                    }
                    else {
                        send.println("00;ROMP;LOGIN;You are not logged");
                    }
                    break;
            }
            request = null;
        }
    }
}
