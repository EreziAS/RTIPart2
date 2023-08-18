package Test;


import db.bean.ActivityBean;
import protocol.FUCAMP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class TestServer {

    public static void main(String[] args) {
        System.out.println("TestServer");
        try{
            ServerSocket serverSocket = new ServerSocket(50017);
            System.out.println("ServerSocket created");

            Socket _clientSocket = serverSocket.accept();
            System.out.println("ClientSocket created");

            BufferedReader in = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
            String line = in.readLine();

            String[] request = line.split(";");


            if(request[1].contains("FUCAMP"))
            {
                System.out.println("FUCAMP");
                ActivityBean bean = new ActivityBean();
                bean.Connect("rti2", "root", "Rotko3");

                FUCAMP fucamp = new FUCAMP();
                fucamp.Processing(request, bean);

            }

            System.out.println(line);


        }catch (Exception e){
            System.out.println("Error: " + e);
        }


    }



}
