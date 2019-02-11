package org.ing1.pds;

public class Controller {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {}

    public void add(String entity, String[] values, Request request) {
        request.setType("add");
        request.setEntity(entity);
        request.setValues(values);
    }

    public void show(String entity, Request request) {
        request.setType("show");
        request.setType(entity);
    }
}
