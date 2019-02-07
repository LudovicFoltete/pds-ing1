package org.ing1.pds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App
{
    public static void main( String[] args )
    {
        ServerSocket serverSocket  ;
        Socket socket ;
        int port = 2121;

        try {

            // server initialization on port specified
            serverSocket = new ServerSocket(port);
            System.out.println("The server is listening to the port " + port);

            // waiting for connection from the client
            socket = serverSocket.accept();
            System.out.println("New connection accepted !");

            // get the input and output stream for this socket
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //communication with the client
            System.out.println(in.readLine());
            out.println("Hi, I'm the server !");
            out.println("Nice to meet you !");
            out.flush();

            // close all
            in.close();
            out.close();
            serverSocket.close();
            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
