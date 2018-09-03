package main;

public class Main
{
    private static final String IP   = "192.168.123.140";
    private static final int    PORT = 9654;

    public static void main(String[] args) throws InterruptedException
    {
        //        Server server = new Server();
        //        Executors.newSingleThreadExecutor().submit(() -> server.start(PORT));
        //        Thread.sleep(500);

        Client client = new Client();
        client.startConnection(IP, PORT);

        Client client2 = new Client();
        client2.startConnection(IP, PORT);

        Client client3 = new Client();
        client3.startConnection(IP, PORT);

        client.sendMsg("siema");
        client2.sendMsg("siema");
        client3.sendMsg("siema");

        client.shutConnection();
        client2.shutConnection();
        client3.shutConnection();

        //        server.stop();
    }

}
