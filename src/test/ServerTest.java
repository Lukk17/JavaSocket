package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;
import main.*;

public class ServerTest
{
    private static final String IP   = "192.168.123.140";
    private static final int    PORT = 3444;

    @BeforeClass
    public static void start() throws InterruptedException
    {
        Server server = new Server();
        Executors.newSingleThreadExecutor().submit(() -> server.start(PORT));
        Thread.sleep(500);
    }

    @Test
    public void givenClient1_whenServerRespond_thenCorrect()
    {
        Client client = new Client();
        client.startConnection(IP, PORT);

        String result1 = client.sendMsg("msg1");
        String result2 = client.sendMsg("breakIt");

        client.shutConnection();
        client.startConnection(IP, PORT);
        String result3 = client.sendMsg("msg2");

        assertEquals("msg1", result1);
        assertEquals("msg2", result3);
        assertEquals("see ya !", result2);
        client.shutConnection();

    }

    @Test
    public void givenClient2_whenServerRespond_thenCorrect()
    {
        Client client2 = new Client();
        client2.startConnection(IP, PORT);

        String result1 = client2.sendMsg("msg1");
        String result2 = client2.sendMsg("breakIt");

        client2.shutConnection();
        client2.startConnection(IP, PORT);
        String result3 = client2.sendMsg("msg2");

        assertEquals("msg1", result1);
        assertEquals("msg2", result3);
        assertEquals("see ya !", result2);
        client2.shutConnection();

    }

    @Test
    public void givenClient3and4_whenServerRespond_thenCorrect()
    {
        Client client3 = new Client();
        client3.startConnection(IP, PORT);

        Client client4 = new Client();
        client4.startConnection(IP, PORT);

        String result1 = client3.sendMsg("msg1");
        String result2 = client3.sendMsg("breakIt");

        String result4 = client4.sendMsg("msg1");
        String result5 = client4.sendMsg("breakIt");

        client3.shutConnection();
        client3.startConnection(IP, PORT);
        String result3 = client3.sendMsg("msg2");

        assertEquals("msg1", result1);
        assertEquals("msg2", result3);
        assertEquals("see ya !", result2);

        assertEquals("msg1", result4);
        assertEquals("see ya !", result5);

        client3.shutConnection();
        client4.shutConnection();

    }

}
