package org.ing1.pds;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class App extends Application
{
    static BufferedReader in;
    static PrintWriter out;


    public static void main( String[] args )
    {
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
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            launch(args);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Shop Management");

        BorderPane mainContainer = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("gui.fxml"));
        AnchorPane middleContainer = loader.load();
        mainContainer.setCenter(middleContainer);
        ShopMapping controller =loader.getController();
        controller.setApp(this);
    }
}
