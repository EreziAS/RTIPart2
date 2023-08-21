package protocol;

import db.bean.ActivityBean;
import db.bean.ActivityLoginBean;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;


// FUCAMP PORT 50017

public class FUCAMP {


    private int _quit = 0;
    private boolean _isLogged = false;

    private String request = null;

    public FUCAMP() {

    }

    public void Processing(ActivityBean bean, BufferedReader receive, PrintWriter send ) throws IOException, SQLException {

        while(_quit == 0)
        {
            if(request==null){
                request = receive.readLine();
            }
            String[] requestArray = request.split(";");

            switch (requestArray[2]){
                case "LOGIN":
                    //Login
                    System.out.println("COMMAND LOGIN");

                    ActivityLoginBean loginBean = new ActivityLoginBean();
                    loginBean.Connect("rti2","root","Rotko3");
                    if(loginBean.Login(requestArray[3],requestArray[4]))
                    {
                        _isLogged = true;
                        send.println("00;FUCAMP;LOGIN;Login success");
                    }
                    else
                    {
                        _isLogged = false;
                        send.println("00;FUCAMP;LOGIN;Login failed");
                    }
                    break;
                case "LOGOUT":
                    //Logout
                    System.out.println("COMMAND LOGOUT");
                    _isLogged = false;
                    send.println("00;FUCAMP;LOGOUT;Logout success");
                    break;
                case "GETALL":
                    //Display all the activities
                    System.out.println("COMMAND GETALL");
                    if(_isLogged)
                    {
                        ResultSet result = bean.getAll();

                        while(result.next())
                        {
                            send.println("00;FUCAMP;GETALL;"+result.getString("id")+";"+result.getString("title")+";"+result.getString("max_participants")+";"+result.getString("participants_registered")+";"+result.getString("duration")+";"+result.getString("price"));
                        }

                    }
                    else
                    {
                        send.println("00;FUCAMP;LOGIN;You are not logged");
                    }


                    break;
                case "GETBYID":
                    //Display the activity with the given id
                    System.out.println("GETBYID");
                    if(_isLogged)
                    {
                        ResultSet result = bean.getById(requestArray[3]);

                        while(result.next())
                        {
                            send.println("00;FUCAMP;GETBYID;"+result.getString("id")+";"+result.getString("title")+";"+result.getString("max_participants")+";"+result.getString("participants_registered")+";"+result.getString("duration")+";"+result.getString("price"));
                        }

                    }
                    else
                    {
                        send.println("00;FUCAMP;LOGIN;You are not logged");
                    }
                    break;
                case "BOOK":
                    //Add an activity
                    System.out.println("BOOK");
                    if(_isLogged)
                    {
                        int result = bean.updateActivity(Integer.parseInt(requestArray[3]),Integer.parseInt(requestArray[4]));

                        if(result == 1)
                        {
                            send.println("00;FUCAMP;BOOK;Inscription OK");
                        }
                        else if(result == 0)
                        {
                            send.println("00;FUCAMP;BOOK;Error max participants exceeded");
                        }
                        else
                        {
                            send.println("00;FUCAMP;BOOK;Error inscription failed");
                        }
                    }
                    else
                    {
                        send.println("00;FUCAMP;LOGIN;You are not logged");
                    }
                    break;
                case "QUIT":
                    //Quit the program
                    System.out.println("QUIT");
                    _quit = 1;
                    break;
                default:
                    System.out.println("Error: Invalid request");
                    break;
            }
            request = null;
        }
    }

}
