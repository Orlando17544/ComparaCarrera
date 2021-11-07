package com.orlandocanchola.enoe;

import com.google.android.material.textfield.TextInputLayout;

public class Edad {
    private String edadMínimaString;
    private String edadMáximaString;
    private int edadMínimaInt;
    private int edadMáximaInt;
    private TextInputLayout edadMínimaLayout;
    private TextInputLayout edadMáximaLayout;
    private String mensajeError;
    private String tipo;
    private Validación validación;
    private Condición condición;

    public Edad(TextInputLayout edadMínimaLayout, TextInputLayout edadMáximaLayout, String tipo) {
        this.edadMínimaLayout = edadMínimaLayout;
        this.edadMáximaLayout = edadMáximaLayout;
        this.tipo = tipo;
        this.validación = new Validación(this);
        this.condición = new Condición(this);
    }

    public void obtenerItem() {
        obtenerString();
        convertirString();
    }

    private void obtenerString() {
        this.edadMínimaString = edadMínimaLayout.getEditText().getText().toString();
        this.edadMáximaString = edadMáximaLayout.getEditText().getText().toString();
    }

    private void convertirString() {
        try {
            edadMínimaInt = Integer.parseInt(edadMínimaString);
            edadMáximaInt = Integer.parseInt(edadMáximaString);
        } catch (NumberFormatException ex) {
        }
    }

    public void mostrarMensaje() {
        if (tipo.equals("edadMínima")) {
            edadMínimaLayout.setError(mensajeError);
        } else {
            edadMáximaLayout.setError(mensajeError);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public String getEdadMínimaString() {
        return edadMínimaString;
    }

    public String getEdadMáximaString() {
        return edadMáximaString;
    }

    public int getEdadMínimaInt() {
        return edadMínimaInt;
    }

    public int getEdadMáximaInt() {
        return edadMáximaInt;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Validación getValidación() {
        return validación;
    }

    public Condición getCondición() {
        return condición;
    }
}
