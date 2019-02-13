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
        int port = 2121;
        String line;

        try {

            // server initialization on port specified
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("The server is listening to the port " + port);

            // waiting for connection from the client
            Socket socket = serverSocket.accept();
            System.out.println("New connection accepted !");

            // get the input and output stream for this socket
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //get the json instance
            Serialization json = SerializationImpl.getInstance();
            StringBuilder jsonData = new StringBuilder();

            //communication with the client
            while (true) {
                while (!(line = in.readLine()).equals("end")) {
                    System.out.println(line);
                    jsonData.append("\n").append(line);
                }
                //write json into java object
                Request request = json.read(jsonData.toString());
                Response response = executeRequest(request);

                //write response into json
                json.write(out, response);
                out.println("\nend");
                out.flush();
                jsonData = new StringBuilder(in.readLine());
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Response executeRequest(Request request) {
        Response response = new Response();
        DAO dao = DAOImpl.getInstance();

        if (request.getEntity().equals("Shop")) {
            response.setEntity("Shop");
            if (request.getType().equals("show")) {
                response.setJavaBeans(dao.getShops());
                return response;
            }
            else if (request.getType().equals("add")) {
                dao.saveShop((request.getValues()));
                return response;
            }
        }
        return null;
    }
}
