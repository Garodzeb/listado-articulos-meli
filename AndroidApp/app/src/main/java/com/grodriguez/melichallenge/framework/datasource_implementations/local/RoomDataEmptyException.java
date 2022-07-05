package com.grodriguez.melichallenge.framework.datasource_implementations.local;

// Custom exception utilizada para informar a la interfaz que no se pudo recuperar información de la
// base de room
public class RoomDataEmptyException extends Exception {

    // Tabla a la que se realizó la consulta
    private String table;
    // Parámetros utilizados
    private String parameters;

    // region GET-SET

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    // endregion

}// End Class
