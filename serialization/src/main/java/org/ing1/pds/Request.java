package org.ing1.pds;

class Request {

    private String type;
    private String entity;
    private String[] fields;
    private String[] values;


    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    String getEntity() {
        return entity;
    }

    void setEntity(String entity) {
        this.entity = entity;
    }

    String[] getFields() {
        return fields;
    }

    void setFields(String[] fields) {
        this.fields = fields;
    }

    String[] getValues() {
        return values;
    }

    void setValues(String[] values) {
        this.values = values;
    }
}
