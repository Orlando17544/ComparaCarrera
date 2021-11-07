package com.orlandocanchola.enoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Object;

public class PreferenciasActivity extends AppCompatActivity {
    private Carrera carrera;
    private Salario salarioMínimo;
    private Salario salarioMáximo;
    private Periodo trimestre;
    private Periodo año;
    private Lugar ciudad;
    private Lugar estado;
    private Lugar municipio;
    private NivelEducativo nivelEducativo;
    private Edad edadMínima;
    private Edad edadMáxima;
    private Sexo sexo;
    private MaterialButton materialButton;
    private String activity;

    public static final String EXTRA_RETURN_CONSULTA = "com.orlandocanchola.enoe.RETURN_CONSULTA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        obtenerTipoActivity();

        MaterialToolbar materialToolbar = findViewById(R.id.materialToolbar);
        TextInputLayout salarioMínimoLayout = findViewById(R.id.salarioMínimo);
        TextInputLayout salarioMáximoLayout = findViewById(R.id.salarioMáximo);
        TextInputLayout trimestreLayout = findViewById(R.id.trimestre);
        TextInputLayout añoLayout = findViewById(R.id.año);
        TextInputLayout estadoLayout = findViewById(R.id.estado);
        TextInputLayout ciudadLayout = findViewById(R.id.ciudad);
        TextInputLayout municipioLayout = findViewById(R.id.municipio);
        TextInputLayout edadMínimaLayout = findViewById(R.id.edadMínima);
        TextInputLayout edadMáximaLayout = findViewById(R.id.edadMáxima);
        TextInputLayout nivelEducativoLayout = findViewById(R.id.nivelEducativo);
        TextInputLayout sexoLayout = findViewById(R.id.sexo);
        ImageView imageView = findViewById(R.id.vector);
        ImageButton ayudaCiudad = findViewById(R.id.ayudaLugarCiudad);
        ImageButton ayudaEstadoMunicipio = findViewById(R.id.ayudaLugarEstadoMunicipio);

        establecerTransición(imageView);
        enlazarVector(imageView);

        if (activity.equals("mapaSalario")) {
            ocultarCamposLugar(ciudadLayout, estadoLayout, municipioLayout);
            ocultarAyudasLugar(ayudaCiudad, ayudaEstadoMunicipio);
        }

        materialButton = (MaterialButton) findViewById(R.id.guardarPreferencias);

        terminarActividad(materialToolbar);

        establecerTítulo(materialToolbar);

        carrera = new Carrera(getIntent());

        salarioMínimo = new Salario(salarioMínimoLayout, salarioMáximoLayout, "salarioMínimo");
        salarioMáximo = new Salario(salarioMínimoLayout, salarioMáximoLayout, "salarioMáximo");

        trimestre = new Periodo(this, trimestreLayout, añoLayout, "trimestre");

        año = new Periodo(this, trimestreLayout, añoLayout, "año");

        ciudad = new Lugar(this, ciudadLayout, estadoLayout, municipioLayout, "ciudad");
        ciudad.enlazarAutoCompleteTextViewAdapter();

        estado = new Lugar(this, ciudadLayout, estadoLayout, municipioLayout, "estado");
        estado.enlazarAutoCompleteTextViewAdapter();

        municipio = new Lugar(this, ciudadLayout, estadoLayout, municipioLayout, "municipio");
        municipio.enlazarAutoCompleteTextViewAdapter();

        estado.getAutoCompleteTextView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                        municipio.enlazarAutoCompleteTextViewAdapter(); }
                                                                });
        nivelEducativo = new NivelEducativo(this, nivelEducativoLayout);

        edadMínima = new Edad(edadMínimaLayout, edadMáximaLayout, "edadMínima");
        edadMáxima = new Edad(edadMínimaLayout, edadMáximaLayout, "edadMáxima");

        sexo = new Sexo(this, sexoLayout);
    }

    public void guardarPreferencias(View view) {
        carrera.obtenerItem();
        salarioMínimo.obtenerItem();
        salarioMáximo.obtenerItem();
        trimestre.obtenerItem();
        año.obtenerItem();
        ciudad.obtenerItem();
        estado.obtenerItem();
        municipio.obtenerItem();
        nivelEducativo.obtenerItem();
        edadMínima.obtenerItem();
        edadMáxima.obtenerItem();
        sexo.obtenerItem();

        if(salarioMínimo.getValidación().esVálido()
                & salarioMáximo.getValidación().esVálido()
                & trimestre.getValidación().esVálido()
                & año.getValidación().esVálido()
                & ciudad.getValidación().esVálido()
                & estado.getValidación().esVálido()
                & municipio.getValidación().esVálido()
                & edadMínima.getValidación().esVálido()
                & edadMáxima.getValidación().esVálido()) {
            salarioMínimo.mostrarMensaje();
            salarioMáximo.mostrarMensaje();
            trimestre.mostrarMensaje();
            año.mostrarMensaje();
            ciudad.mostrarMensaje();
            estado.mostrarMensaje();
            municipio.mostrarMensaje();
            edadMínima.mostrarMensaje();
            edadMáxima.mostrarMensaje();
            String consulta = Consulta.generarConsulta(
                    activity,
                    carrera.getCondición().generarCondición(),
                    salarioMínimo.getCondición().generarCondición(),
                    trimestre.getCondición().generarCondición(),
                    ciudad.getCondición().generarCondición(),
                    estado.getCondición().generarCondición(),
                    municipio.getCondición().generarCondición(),
                    nivelEducativo.getCondición().generarCondición(),
                    edadMínima.getCondición().generarCondición(),
                    sexo.getCondición().generarCondición()
            );
            //Descomentar para ver como se genera la consulta en ejecución.
            //Toast.makeText(this, consulta, Toast.LENGTH_LONG).show();
            establecerResultadoExitoso(consulta);
            Snackbar.make(materialButton, "Las preferencias se guardaron exitosamente", Snackbar.LENGTH_LONG)
                    .setAnchorView(materialButton).show();
        } else {
            salarioMínimo.mostrarMensaje();
            salarioMáximo.mostrarMensaje();
            trimestre.mostrarMensaje();
            año.mostrarMensaje();
            ciudad.mostrarMensaje();
            estado.mostrarMensaje();
            municipio.mostrarMensaje();
            edadMínima.mostrarMensaje();
            edadMáxima.mostrarMensaje();
        }
    }

    private void establecerTransición(ImageView imageView) {
        imageView.setTransitionName(activity + "Vector");
    }

    private void enlazarVector(ImageView imageView) {
        int recurso = 0;
        switch (activity) {
            case "histogramaSalario":
                recurso = R.drawable.ic_histograma_salario_vector;
                break;
            case "tendenciaSalario":
                recurso = R.drawable.ic_tendencia_salario_vector;
                break;
            case "mapaSalario":
                recurso = R.drawable.ic_mapa_salario_vector;
                break;
        }
        imageView.setImageResource(recurso);

    }

    private void establecerTítulo(MaterialToolbar materialToolbar) {
        String título = null;
        switch (activity) {
            case "histogramaSalario":
                título = "Histograma de Salarios";
                break;
            case "tendenciaSalario":
                título = "Tendencia de Salarios";
                break;
            case "mapaSalario":
                título = "Mapa de Salarios";
                break;
        }
        materialToolbar.setTitle(título);
    }

    private void terminarActividad(MaterialToolbar materialToolbar) {
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenciasActivity.this.finishAfterTransition();
            }
        });
    }

    private void obtenerTipoActivity() {
        Intent intent = getIntent();
        activity = intent.getStringExtra(ConsultasActivity.EXTRA_ACTIVITY);
    }

    private void ocultarCamposLugar(TextInputLayout ciudadLayout, TextInputLayout estadoLayout, TextInputLayout municipioLayout) {
        ciudadLayout.setVisibility(View.GONE);
        estadoLayout.setVisibility(View.GONE);
        municipioLayout.setVisibility(View.GONE);
    }

    private void ocultarAyudasLugar(ImageButton ayudaCiudad, ImageButton ayudaEstadoMunicipio) {
        ayudaCiudad.setVisibility(View.GONE);
        ayudaEstadoMunicipio.setVisibility(View.GONE);
    }

    public void mostrarAyuda(View view) {
        String mensajeAyuda = new String();
        switch (view.getId()) {
            case R.id.ayudaSalario:
                mensajeAyuda = "Si ingresas salario mínimo tienes que ingresar salario máximo";
                break;
            case R.id.ayudaPeriodo:
                mensajeAyuda = "Si ingresas trimestre tienes que ingresar año";
                break;
            case R.id.ayudaLugarCiudad:
                mensajeAyuda = "Si ingresas ciudad no puedes ingresar estado y/o municipio";
                break;
            case R.id.ayudaLugarEstadoMunicipio:
                mensajeAyuda = "Si ingresas municipio tienes que ingresar estado, si ingresas estado puedes no ingresar municipio";
                break;
            case R.id.ayudaNivelEducativo:
                mensajeAyuda = "Puedes consultar por licenciatura, maestría o doctorado";
                break;
            case R.id.ayudaEdad:
                mensajeAyuda = "Si ingresas edad mínima tienes que ingresar edad máxima";
                break;
            case R.id.ayudaSexo:
                mensajeAyuda = "Puedes consultar solamente por hombres o mujeres";
                break;
        }
        Toast.makeText(this, mensajeAyuda, Toast.LENGTH_LONG).show();
    }

    private void establecerResultadoExitoso(String consulta) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_RETURN_CONSULTA, consulta);
        setResult(RESULT_OK, returnIntent);
    }
}
