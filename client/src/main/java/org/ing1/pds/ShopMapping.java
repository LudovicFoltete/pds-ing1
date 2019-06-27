package org.ing1.pds;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ShopMapping {

    @FXML
    private TableView<Shop> shopTable;
    @FXML
    private TableColumn<Shop, String> nameColumn;
    @FXML
    private TableColumn<Shop, String> categoryColumn;
    @FXML
    private TableColumn<Shop, Number> locationColumn;
    @FXML
    private TableColumn<Shop, String> phoneColumn;
    @FXML
    private TableColumn<Shop, String> emailColumn;

    private App app;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        locationColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLocation_id()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
    }

    @FXML
    public void start() {

        //init the request
        Request request = new Request();
        String line;
        StringBuilder jsonData = new StringBuilder();

        try {
            request.setType("show");
            request.setEntity("Shop");

            //convert Request to json string
            Serialization json = SerializationImpl.getInstance();
            json.write(App.out, request);

            // communication with the server
            App.out.println("\nend");
            App.out.flush();
            System.out.println("Completed");

            while (!(line = App.in.readLine()).equals("end")) {
                jsonData.append(line);
            }

            Response response = json.read(jsonData.toString(), Response.class);
            ObservableList<Shop> listShop = FXCollections.observableArrayList();
            listShop.addAll(response.getShops());

            shopTable.setItems(listShop);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

}
