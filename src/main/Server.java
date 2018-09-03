package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server
{
    private static Logger    log  = Logger.getLogger(Server.class.getName());
    private static final int PORT = 9654;

    private ServerSocket serverSocket;

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start(PORT);
    }

    public void start(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
            while (true)
            {
                new ClientHandler(serverSocket.accept()).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stop();
        }

    }

    public void stop()
    {
        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread
    {
        private Socket         clientSocket;
        private PrintWriter    out;
        private BufferedReader in;

        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            try
            {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;

                while ((inputLine = in.readLine()) != null)
                {
                    log.info(inputLine);
                    if ("breakIt".equals(inputLine))
                    {
                        out.println("see ya !");
                        break;
                    }
                    out.println(inputLine);

                }

                out.close();
                in.close();
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }
}
