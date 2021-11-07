package com.orlandocanchola.enoe;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Periodo {
    private Context context;
    private TextInputLayout trimestreLayout;
    private TextInputLayout añoLayout;
    private String trimestre;
    private String año;
    private String periodoString;
    private int periodoInt;
    private String mensajeError;
    private String tipo;
    private Validación validación;
    private Condición condición;

    public Periodo(Context context, TextInputLayout trimestreLayout, TextInputLayout añoLayout, String tipo) {
        this.context = context;
        this.trimestreLayout = trimestreLayout;
        this.añoLayout = añoLayout;
        this.tipo = tipo;
        this.validación = new Validación(this);
        this.condición = new Condición(this);
        enlazarAutoCompleteTextViewAdapter();
    }

    private void convertirPeriodo() {
        StringBuffer periodoBuffer = new StringBuffer();
        switch(año) {
            case "2012":
                periodoBuffer.append("12");
                break;
            case "2013":
                periodoBuffer.append("13");
                break;
            case "2014":
                periodoBuffer.append("14");
                break;
            case "2015":
                periodoBuffer.append("15");
                break;
            case "2016":
                periodoBuffer.append("16");
                break;
            case "2017":
                periodoBuffer.append("17");
                break;
            case "2018":
                periodoBuffer.append("18");
                break;
            case "2019":
                periodoBuffer.append("19");
                break;
            default:
                periodoBuffer.append("");
                break;
        }
        switch(trimestre) {
            case "Enero-Marzo":
                periodoBuffer.append("1");
                break;
            case "Abril-Junio":
                periodoBuffer.append("2");
                break;
            case "Julio-Septiembre":
                periodoBuffer.append("3");
                break;
            case "Octubre-Diciembre":
                periodoBuffer.append("4");
                break;
            default:
                periodoBuffer.append("");
                break;
        }
        periodoString = periodoBuffer.toString();
        convertirString();
    }

    private void convertirString() {
        try {
            periodoInt = Integer.valueOf(periodoString);
        } catch (NumberFormatException ex) {

        }
    }

    private void enlazarAutoCompleteTextViewAdapter() {
        if (tipo.equals("trimestre")) {
            ArrayList<String> trimestres = new ArrayList<String>(Collections.singleton(""));
            trimestres.addAll(Arrays.asList(context.getResources().getStringArray(R.array.trimestres)));
            ArrayAdapter adapterTrimestre = new ArrayAdapter(context, R.layout.exposed_dropdown_item, trimestres);
            AutoCompleteTextView autoCompleteTextViewTrimestre = (AutoCompleteTextView) trimestreLayout.getEditText();
            autoCompleteTextViewTrimestre.setAdapter(adapterTrimestre);
        } else {
            ArrayList<String> años = new ArrayList<String>(Collections.singleton(""));
            años.addAll(Arrays.asList(context.getResources().getStringArray(R.array.años)));
            ArrayAdapter adapterAño = new ArrayAdapter(context, R.layout.exposed_dropdown_item, años);
            AutoCompleteTextView autoCompleteTextViewAño = (AutoCompleteTextView) añoLayout.getEditText();
            autoCompleteTextViewAño.setAdapter(adapterAño);
        }
    }

    public void obtenerItem() {
        trimestre = trimestreLayout.getEditText().getText().toString();
        año = añoLayout.getEditText().getText().toString();
        convertirPeriodo();
    }

    public void mostrarMensaje() {
        if (tipo.equals("trimestre")) {
            trimestreLayout.setError(mensajeError);
        } else {
            añoLayout.setError(mensajeError);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public String getPeriodoString() {
        return periodoString;
    }

    public int getPeriodoInt() {
        return periodoInt;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public String getAño() {
        return año;
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
