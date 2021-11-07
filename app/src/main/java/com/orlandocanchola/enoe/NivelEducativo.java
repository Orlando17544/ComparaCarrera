package com.orlandocanchola.enoe;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NivelEducativo {
    private Context context;
    private TextInputLayout nivelEducativoLayout;
    private String nivelEducativo;
    private int posición;
    private String tipo = "nivelEducativo";
    private Condición condición;

    public NivelEducativo(Context context, TextInputLayout nivelEducativoLayout) {
        this.context = context;
        this.nivelEducativoLayout = nivelEducativoLayout;
        this.condición = new Condición(this);
        enlazarAutoCompleteTextViewAdapter();
    }

    private void enlazarAutoCompleteTextViewAdapter() {
        ArrayList<String> niveles = new ArrayList<String>(Collections.singleton(""));
        niveles.addAll(Arrays.asList(context.getResources().getStringArray(R.array.niveles)));
        ArrayAdapter adapterNivelEducativo = new ArrayAdapter(context, R.layout.exposed_dropdown_item, niveles);
        AutoCompleteTextView autoCompleteTextViewNivelEducativo = (AutoCompleteTextView) nivelEducativoLayout.getEditText();
        autoCompleteTextViewNivelEducativo.setAdapter(adapterNivelEducativo);
    }

    public void obtenerItem() {
        nivelEducativo = nivelEducativoLayout.getEditText().getText().toString();
        obtenerPosición();
    }

    private void obtenerPosición() {
        switch(nivelEducativo) {
            case "Licenciatura":
                posición = 7;
                break;
            case "Maestría":
                posición = 8;
                break;
            case "Doctorado":
                posición = 9;
                break;
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getPosición() {
        return posición;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public Condición getCondición() {
        return condición;
    }
}
