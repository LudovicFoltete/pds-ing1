package org.ing1.pds;

public class Controller {
    private static Controller ourInstance = new Controller();

    static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {}

    void add(String entity, String[] values, Request request) {
        request.setType("add");
        request.setEntity(entity);
        request.setValues(values);
    }

    void show(String entity, Request request) {
        request.setType("show");
        request.setEntity(entity);
    }
}
