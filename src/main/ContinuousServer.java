package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ContinuousServer
{
    private ServerSocket   serverSocket;
    private Socket         clientSocket;
    private PrintWriter    out;
    private BufferedReader in;

    public static void main(String[] args)
    {
        ContinuousServer continuousServer = new ContinuousServer();
        continuousServer.start(9911);
    }

    public void start(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {

                if ("breakIt".equals(inputLine))
                {
                    out.println("see ya !");
                    break;
                }
                out.println(inputLine);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void stop()
    {
        try
        {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
