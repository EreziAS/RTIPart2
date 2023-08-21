package Test;


import server.GenericServer;

public class TestServer {

    public static void main(String[] args) {
        System.out.println("TestServer");

        try {
            GenericServer server = new GenericServer(50018);
            server.start();
        }catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
