package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;
import main.*;

public class SimpleSingleConServerTest
{
    @BeforeClass
    public static void start() throws InterruptedException
    {
        Executors.newSingleThreadExecutor().submit(() -> new SimpleSingleConServer().start(9777));
        Thread.sleep(500);

    }

    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect()
    {
        Client client = new Client();
        client.startConnection("192.168.123.140", 9777);

        String result = client.sendMsg("hello server");
        assertEquals("hello client", result);

    }

}
