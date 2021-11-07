package com.orlandocanchola.enoe;

import android.content.Context;
import android.content.Intent;

public class Carrera {
    private Intent intent;
    private int claveCarrera;
    private String tipo = "carrera";
    private Condición condición;

    public Carrera(Intent intent) {
        this.intent = intent;
        this.condición = new Condición(this);
    }

    public void obtenerItem() {
        int posición = intent.getIntExtra(CamposProgramas.EXTRA_POSICIÓN, 0);
        this.claveCarrera = CamposProgramas.getCamposProgramas()[posición].getCampoDetalladoClave();
    }

    public int getClaveCarrera() {
        return claveCarrera;
    }

    public String getTipo() {
        return tipo;
    }

    public Condición getCondición() {
        return condición;
    }
}
