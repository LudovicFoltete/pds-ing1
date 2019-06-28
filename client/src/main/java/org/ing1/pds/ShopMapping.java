package org.ing1.pds;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label idValue;
    @FXML
    private Label areaValue;
    @FXML
    private Label minValue;
    @FXML
    private Label maxValue;


    private App app;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        shopTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> initializeDescription(newValue));
    }

    private void initializeDescription(Shop s) {
        idValue.setText("");
        areaValue.setText("");
        minValue.setText("");
        maxValue.setText("");

        if(s != null) {
            idValue.setText(String.valueOf(s.getLocation().getId()));
            areaValue.setText(String.valueOf(s.getLocation().getArea()));
            minValue.setText(String.valueOf(s.getMin_area()));
            maxValue.setText(String.valueOf(s.getMax_area()));
        }
    }

    //call by the menu
    public void select() {

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
