package com.orlandocanchola.enoe;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sexo {
    private Context context;
    private TextInputLayout sexoLayout;
    private String sexo;
    private ArrayList<String> sexos;
    private int posición;
    private String tipo = "sexo";
    private Condición condición;

    public Sexo(Context context, TextInputLayout sexoLayout) {
        this.context = context;
        this.sexoLayout = sexoLayout;
        this.condición = new Condición(this);
        enlazarAutoCompleteTextViewAdapter();
    }

    private void enlazarAutoCompleteTextViewAdapter() {
        sexos = new ArrayList<String>(Collections.singleton(""));
        sexos.addAll(Arrays.asList(context.getResources().getStringArray(R.array.sexos)));
        ArrayAdapter adapterSexo = new ArrayAdapter(context, R.layout.exposed_dropdown_item, sexos);
        AutoCompleteTextView autoCompleteTextViewSexo = (AutoCompleteTextView) sexoLayout.getEditText();
        autoCompleteTextViewSexo.setAdapter(adapterSexo);
    }

    public void obtenerItem() {
        sexo = sexoLayout.getEditText().getText().toString();
        obtenerPosición();
    }

    private void obtenerPosición() {
        posición = sexos.indexOf(sexo);
    }

    public String getTipo() {
        return tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public int getPosición() {
        return posición;
    }

    public Condición getCondición() {
        return condición;
    }
}
