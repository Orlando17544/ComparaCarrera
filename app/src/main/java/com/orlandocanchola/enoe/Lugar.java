package com.orlandocanchola.enoe;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lugar {
    private Context context;
    private TextInputLayout ciudadLayout;
    private TextInputLayout estadoLayout;
    private TextInputLayout municipioLayout;
    private String ciudad;
    private String estado;
    private String municipio;
    private String mensajeError;
    private AutoCompleteTextView autoCompleteTextViewCiudad;
    private AutoCompleteTextView autoCompleteTextViewEstado;
    private int posición;
    private ArrayList<String> ciudades;
    private ArrayList<String> estados;
    private ArrayList<String> municipios;
    private String tipo;
    private Validación validación;
    private Condición condición;

    public Lugar(Context context, TextInputLayout ciudadLayout, TextInputLayout estadoLayout, TextInputLayout municipioLayout, String tipo) {
        this.context = context;
        this.ciudadLayout = ciudadLayout;
        this.estadoLayout = estadoLayout;
        this.municipioLayout = municipioLayout;
        this.tipo = tipo;
        this.validación = new Validación(this);
        this.condición = new Condición(this);
    }

    public void enlazarAutoCompleteTextViewAdapter() {
        if (tipo.equals("ciudad")) {
            ciudades = new ArrayList<String>(Collections.singleton(""));
            ciudades.addAll(Arrays.asList(context.getResources().getStringArray(R.array.ciudades)));
            ArrayAdapter adapterCiudad = new ArrayAdapter(context, R.layout.exposed_dropdown_item, ciudades);
            autoCompleteTextViewCiudad = (AutoCompleteTextView) ciudadLayout.getEditText();
            autoCompleteTextViewCiudad.setAdapter(adapterCiudad);
        } else if (tipo.equals("estado")) {
            estados = new ArrayList<String>(Collections.singleton(""));
            estados.addAll(Arrays.asList(context.getResources().getStringArray(R.array.estados)));
            ArrayAdapter adapterEstado = new ArrayAdapter(context, R.layout.exposed_dropdown_item, estados);
            autoCompleteTextViewEstado = (AutoCompleteTextView) estadoLayout.getEditText();
            autoCompleteTextViewEstado.setAdapter(adapterEstado);
        } else {
            municipios = new ArrayList<String>(Collections.singleton(""));
            switch (estadoLayout.getEditText().getText().toString()) {
                case "":
                    break;
                case "Aguascalientes":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 0, 11)));
                    break;
                case "Baja California":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 11, 16)));
                    break;
                case "Baja California Sur":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 16, 21)));
                    break;
                case "Campeche":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 21, 32)));
                    break;
                case "Coahuila de Zaragoza":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 32, 70)));
                    break;
                case "Colima":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 70, 80)));
                    break;
                case "Chiapas":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 80, 203)));
                    break;
                case "Chihuahua":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 203, 270)));
                    break;
                case "Ciudad de México":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 270, 286)));
                    break;
                case "Durango":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 286, 325)));
                    break;
                case "Guanajuato":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 325, 371)));
                    break;
                case "Guerrero":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 371, 452)));
                    break;
                case "Hidalgo":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 452, 536)));
                    break;
                case "Jalisco":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 536, 661)));
                    break;
                case "México":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 661, 786)));
                    break;
                case "Michoacán de Ocampo":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 786, 899)));
                    break;
                case "Morelos":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 899, 934)));
                    break;
                case "Nayarit":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 934, 954)));
                    break;
                case "Nuevo León":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 954, 1005)));
                    break;
                case "Oaxaca":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1005, 1575)));
                    break;
                case "Puebla":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1575, 1792)));
                    break;
                case "Querétaro":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1792, 1810)));
                    break;
                case "Quintana Roo":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1810, 1821)));
                    break;
                case "San Luis Potosí":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1821, 1879)));
                    break;
                case "Sinaloa":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1879, 1897)));
                    break;
                case "Sonora":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1897, 1969)));
                    break;
                case "Tabasco":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1969, 1986)));
                    break;
                case "Tamaulipas":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 1986, 2029)));
                    break;
                case "Tlaxcala":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 2029, 2089)));
                    break;
                case "Veracruz de Ignacio de la Llave":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 2089, 2301)));
                    break;
                case "Yucatán":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 2301, 2407)));
                    break;
                case "Zacatecas":
                    municipios.addAll(Arrays.asList(Arrays.copyOfRange(context.getResources().getStringArray(R.array.municipios), 2407, 2465)));
                    break;
            }
            ArrayAdapter adapterMunicipio = new ArrayAdapter(context, R.layout.exposed_dropdown_item, municipios);
            AutoCompleteTextView autoCompleteTextViewMunicipio = (AutoCompleteTextView) municipioLayout.getEditText();
            autoCompleteTextViewMunicipio.setAdapter(adapterMunicipio);
            municipioLayout.getEditText().setText("");
        }
    }

    public void obtenerItem() {
        ciudad = ciudadLayout.getEditText().getText().toString();
        estado = estadoLayout.getEditText().getText().toString();
        municipio = municipioLayout.getEditText().getText().toString();
        obtenerPosición();
    }

    public void mostrarMensaje() {
        if (tipo.equals("ciudad")) {
            ciudadLayout.setError(mensajeError);
        } else if (tipo.equals("estado")) {
            estadoLayout.setError(mensajeError);
        } else {
            municipioLayout.setError(mensajeError);
        }
    }

    private void obtenerPosición() {
        if (tipo.equals("ciudad")) {
            int índice = ciudades.indexOf(ciudad);
            if (índice <= 10) {
                posición = índice;
            } else {
                switch (ciudad) {
                    case "Veracruz":
                        posición = 12;
                        break;
                    case "Acapulco":
                        posición = 13;
                        break;
                    case "Aguascalientes":
                        posición = 14;
                        break;
                    case "Morelia":
                        posición = 15;
                        break;
                    case "Toluca":
                        posición = 16;
                        break;
                    case "Saltillo":
                        posición = 17;
                        break;
                    case "Villahermosa":
                        posición = 18;
                        break;
                    case "Tuxtla Gutiérrez":
                        posición = 19;
                        break;
                    case "Ciudad Juárez":
                        posición = 20;
                        break;
                    case "Tijuana":
                        posición = 21;
                        break;
                    case "Culiacán":
                        posición = 24;
                        break;
                    case "Hermosillo":
                        posición = 25;
                        break;
                    case "Durango":
                        posición = 26;
                        break;
                    case "Tepic":
                        posición = 27;
                        break;
                    case "Campeche":
                        posición = 28;
                        break;
                    case "Cuernavaca":
                        posición = 29;
                        break;
                    case "Oaxaca":
                        posición = 31;
                        break;
                    case "Zacatecas":
                        posición = 32;
                        break;
                    case "Colima":
                        posición = 33;
                        break;
                    case "Querétaro":
                        posición = 36;
                        break;
                    case "Tlaxcala":
                        posición = 39;
                        break;
                    case "La Paz":
                        posición = 40;
                        break;
                    case "Cancún":
                        posición = 41;
                        break;
                    case "Pachuca":
                        posición = 43;
                        break;
                    case "Mexicali":
                        posición = 44;
                        break;
                    case "Reynosa":
                        posición = 46;
                        break;
                }
            }
        } else if (tipo.equals("estado")) {
            posición = estados.indexOf(estado);
        } else {
            int índice = municipios.indexOf(municipio);
            if (estado.equals("Baja California Sur") && índice >= 8) {
                switch (municipio) {
                    case "Los Cabos":
                        posición = 8;
                        break;
                    case "Loreto":
                        posición = 9;
                        break;
                }
            } else if (estado.equals("Chiapas") && índice >= 95) {
                posición = ++índice;
            } else if (estado.equals("Ciudad de México")) {
                posición = ++índice;
            } else {
                posición = índice;
            }
        }
    }

    public String getTipo() {
        return tipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public int getPosición() {
        return posición;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public AutoCompleteTextView getAutoCompleteTextView() {
        return autoCompleteTextViewEstado;
    }

    public Validación getValidación() {
        return validación;
    }

    public Condición getCondición() {
        return condición;
    }
}
