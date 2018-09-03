package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.*;

public class ContinuousServerTest
{
    private Client client = new Client();

    private static final int PORT = 9911;

    @BeforeClass
    public static void start() throws InterruptedException
    {
        Executors.newSingleThreadExecutor().submit(() -> new ContinuousServer().start(PORT));
        Thread.sleep(500);
    }

    @Before
    public void setup()
    {
        client.startConnection("192.168.123.140", PORT);
    }

    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect()
    {
        String result1 = client.sendMsg("msg");
        String result2 = client.sendMsg("smt");
        String result3 = client.sendMsg("breakIt");

        assertEquals("msg", result1);
        assertEquals("smt", result2);
        assertEquals("see ya !", result3);
    }

    @After
    public void close()
    {
        client.shutConnection();
    }

}
