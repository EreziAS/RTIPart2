package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class TestClient {

    public static void main(String[] args) {
        System.out.println("TestClient");

        //TestFUCAMP();
        TestROMP();


    }

    public static void TestFUCAMP(){

        try {
            Socket socket = new Socket("localhost", 50017);
            System.out.println("Socket created");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("50;FUCAMP;LOGIN;Alexis;Sacre");

            System.out.println("Request sent");

            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = receive.readLine();

            System.out.println("Response received: " + line);

            out.println("50;FUCAMP;GETBYID;1");

            while((line = receive.readLine()) != null)
            {
                System.out.println("Response received: " + line);
            }

            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void TestROMP(){

    }
}
