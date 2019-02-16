package org.ing1.pds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class App
{
    public static void main( String[] args )
    {
        String line;

        InetAddress serverAddress;

        try {

            //load the port from config.properties
            int port = PropertiesLoader.getInstance().getPort();

            // get the firewall's IP address
            serverAddress = InetAddress.getByName("firewall");

            // request a connection to the server
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected !");

            // get the input and output streams for this socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            //init the request
            Request request = new Request();

            while (true) {
                //read the request
                View.getInstance().readConsole(request);

                //convert Request to json string
                Serialization json = SerializationImpl.getInstance();
                json.write(out, request);

                // communication with the server
                out.println("\nend");
                out.flush();
                System.out.println("Completed");

                while (!(line = in.readLine()).equals("end")) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
