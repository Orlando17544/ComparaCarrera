package com.orlandocanchola.enoe;

import com.google.android.material.textfield.TextInputLayout;

public class Salario {
    private String salarioMínimoString;
    private String salarioMáximoString;
    private int salarioMínimoInt;
    private int salarioMáximoInt;
    private TextInputLayout salarioMínimoLayout;
    private TextInputLayout salarioMáximoLayout;
    private String mensajeError;
    private Validación validación;
    private Condición condición;
    private String tipo;


    public Salario(TextInputLayout salarioMínimoLayout, TextInputLayout salarioMáximoLayout, String tipo) {
        this.salarioMínimoLayout = salarioMínimoLayout;
        this.salarioMáximoLayout = salarioMáximoLayout;
        this.tipo = tipo;
        this.validación = new Validación(this);
        this.condición = new Condición(this);
    }

    public void obtenerItem() {
        obtenerString();
        convertirString();
    }

    private void obtenerString() {
        this.salarioMínimoString = salarioMínimoLayout.getEditText().getText().toString();
        this.salarioMáximoString = salarioMáximoLayout.getEditText().getText().toString();
    }

    private void convertirString() {
        try {
            salarioMínimoInt = Integer.parseInt(salarioMínimoString);
            salarioMáximoInt = Integer.parseInt(salarioMáximoString);
        } catch (NumberFormatException ex) {}
    }

    public void mostrarMensaje() {
        if (tipo.equals("salarioMínimo")) {
            salarioMínimoLayout.setError(mensajeError);
        } else {
            salarioMáximoLayout.setError(mensajeError);
        }
    }

    public String getSalarioMínimoString() {
        return salarioMínimoString;
    }

    public String getSalarioMáximoString() {
        return salarioMáximoString;
    }

    public int getSalarioMínimoInt() {
        return salarioMínimoInt;
    }

    public int getSalarioMáximoInt() {
        return salarioMáximoInt;
    }

    public Validación getValidación() {
        return validación;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getTipo() {
        return tipo;
    }

    public Condición getCondición() {
        return condición;
    }
}
