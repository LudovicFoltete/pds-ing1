package org.ing1.pds;

import javafx.fxml.FXML;

public class ShopMenuMapping {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void select() {
        app.controller.select();
    }

    @FXML
    public void run() {
        System.out.println("Completed !");
    }

}
