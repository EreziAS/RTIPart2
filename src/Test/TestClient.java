package Test;

import java.io.PrintWriter;
import java.net.*;

public class TestClient {

    public static void main(String[] args) {
        System.out.println("TestClient");

        try {
            Socket socket = new Socket("localhost", 50017);
            System.out.println("Socket created");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("50;FUCAMP;GETALL;");



            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
