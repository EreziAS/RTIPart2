package Test;


import db.bean.ActivityBean;
import db.bean.GenericBean;
import protocol.FUCAMP;
import server.GenericServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class TestServer {

    public static void main(String[] args) {
        System.out.println("TestServer");

        try {
            GenericServer server = new GenericServer(50017);
            server.start();
        }catch (Exception e)
        {
            System.out.println("Error: " + e);
        }


    }



}
