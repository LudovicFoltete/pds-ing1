package org.ing1.pds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class App
{
    public static void main( String[] args )
    {
        Socket socket;
        int port = 2121;

        InetAddress serverAddress;

        try {
            // get the firewall's IP address
            serverAddress = InetAddress.getByName("firewall");

            // request a connection to the server
            socket = new Socket(serverAddress, port);
            System.out.println("Connected !");

            // get the input and output streams for this socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            // communication with the client
            out.println("Hi ! I'm the client !");
            out.flush();
            System.out.println(in.readLine());
            System.out.println(in.readLine());

            // close all
            in.close();
            out.close();
            socket.close();

        }catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
