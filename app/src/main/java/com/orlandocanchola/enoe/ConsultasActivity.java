package com.orlandocanchola.enoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

public class ConsultasActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TRANSICIÓN_NOMBRE_HISTOGRAMA_SALARIO = "histogramaSalarioVector";
    public static final String TRANSICIÓN_NOMBRE_TENDENCIA_SALARIO = "tendenciaSalarioVector";
    public static final String TRANSICIÓN_NOMBRE_MAPA_SALARIO = "mapaSalarioVector";

    public static final String EXTRA_ACTIVITY = "extraActivity";

    public static final int CONSULTA_HISTOGRAMA_SALARIO_REQUEST = 1;
    public static final int CONSULTA_TENDENCIA_SALARIO_REQUEST = 2;
    public static final int CONSULTA_MAPA_SALARIO_REQUEST = 3;

    private int posición;

    private ViewGroup rootView;

    private MaterialCardView histogramaSalarioCard;
    private MaterialCardView tendenciaSalarioCard;
    private MaterialCardView mapaSalarioCard;

    private ImageButton histogramaSalarioFlecha;
    private ImageButton tendenciaSalarioFlecha;
    private ImageButton mapaSalarioFlecha;

    private LinearLayout histogramaSalarioLinear;
    private LinearLayout tendenciaSalarioLinear;
    private LinearLayout mapaSalarioLinear;

    private View histogramaSalarioVector;
    private View tendenciaSalarioVector;
    private View mapaSalarioVector;

    private String consultaHistogramaSalario = new String();
    private String consultaTendenciaSalario = new String();
    private String consultaMapaSalario = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        obtenerCampoDetallado();

        rootView = (ViewGroup) findViewById(R.id.constraintLayout);

        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.materialToolbar);

        histogramaSalarioCard = (MaterialCardView) findViewById(R.id.histogramaSalarioCard);
        tendenciaSalarioCard = (MaterialCardView) findViewById(R.id.tendenciaSalarioCard);
        mapaSalarioCard = (MaterialCardView) findViewById(R.id.mapaSalarioCard);

        histogramaSalarioFlecha = (ImageButton) findViewById(R.id.histogramaSalarioFlecha);
        tendenciaSalarioFlecha = (ImageButton) findViewById(R.id.tendenciaSalarioFlecha);
        mapaSalarioFlecha = (ImageButton) findViewById(R.id.mapaSalarioFlecha);

        histogramaSalarioLinear = (LinearLayout) findViewById(R.id.histogramaSalarioLinear);
        tendenciaSalarioLinear = (LinearLayout) findViewById(R.id.tendenciaSalarioLinear);
        mapaSalarioLinear = (LinearLayout) findViewById(R.id.mapaSalarioLinear);

        histogramaSalarioFlecha.setOnClickListener(this);
        tendenciaSalarioFlecha.setOnClickListener(this);
        mapaSalarioFlecha.setOnClickListener(this);

        terminarActivity(materialToolbar);

        histogramaSalarioVector = (View) findViewById(R.id.histogramaSalarioVector);
        tendenciaSalarioVector = (View) findViewById(R.id.tendenciaSalarioVector);
        mapaSalarioVector = (View) findViewById(R.id.mapaSalarioVector);

        histogramaSalarioCard.setOnClickListener(this);
        tendenciaSalarioCard.setOnClickListener(this);
        mapaSalarioCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickFlecha(v);
        clickCard(v);
    }

    private void clickFlecha(View v) {
        LinearLayout linearLayout;
        ImageButton flecha;
        switch(v.getId()) {
            case R.id.histogramaSalarioFlecha:
                linearLayout = histogramaSalarioLinear;
                flecha = histogramaSalarioFlecha;
                break;
            case R.id.tendenciaSalarioFlecha:
                linearLayout = tendenciaSalarioLinear;
                flecha = tendenciaSalarioFlecha;
                break;
            case R.id.mapaSalarioFlecha:
                linearLayout = mapaSalarioLinear;
                flecha = mapaSalarioFlecha;
                break;
            default:
                linearLayout = null;
                flecha = null;
                return;
        }
        if (linearLayout.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(rootView, new AutoTransition());
            linearLayout.setVisibility(View.VISIBLE);
            flecha.setImageResource(R.drawable.ic_arrow_up);
        } else {
            TransitionManager.beginDelayedTransition(rootView, new AutoTransition());
            linearLayout.setVisibility(View.GONE);
            flecha.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    private void clickCard(View v) {
        Intent intent = null;
        ActivityOptions options = null;
        int requestCode = 0;
        switch (v.getId()) {
            case R.id.histogramaSalarioCard:
                intent = new Intent(ConsultasActivity.this, PreferenciasActivity.class);
                intent.putExtra(EXTRA_ACTIVITY, "histogramaSalario");
                options = ActivityOptions.makeSceneTransitionAnimation(ConsultasActivity.this, histogramaSalarioVector, TRANSICIÓN_NOMBRE_HISTOGRAMA_SALARIO);
                requestCode = CONSULTA_HISTOGRAMA_SALARIO_REQUEST;
                break;
            case R.id.tendenciaSalarioCard:
                intent = new Intent(ConsultasActivity.this, PreferenciasActivity.class);
                intent.putExtra(EXTRA_ACTIVITY, "tendenciaSalario");
                options = ActivityOptions.makeSceneTransitionAnimation(ConsultasActivity.this, tendenciaSalarioVector, TRANSICIÓN_NOMBRE_TENDENCIA_SALARIO);
                requestCode = CONSULTA_TENDENCIA_SALARIO_REQUEST;
                break;
            case R.id.mapaSalarioCard:
                intent = new Intent(ConsultasActivity.this, PreferenciasActivity.class);
                intent.putExtra(EXTRA_ACTIVITY, "mapaSalario");
                options = ActivityOptions.makeSceneTransitionAnimation(ConsultasActivity.this, mapaSalarioVector, TRANSICIÓN_NOMBRE_MAPA_SALARIO);
                requestCode = CONSULTA_MAPA_SALARIO_REQUEST;
                break;
            default:
                return;
        }
        intent.putExtra(CamposProgramas.EXTRA_POSICIÓN, posición);
        startActivityForResult(intent, requestCode, options.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONSULTA_HISTOGRAMA_SALARIO_REQUEST && resultCode == RESULT_OK)
            consultaHistogramaSalario = data.getStringExtra(PreferenciasActivity.EXTRA_RETURN_CONSULTA);
        if (requestCode == CONSULTA_TENDENCIA_SALARIO_REQUEST && resultCode == RESULT_OK)
            consultaTendenciaSalario = data.getStringExtra(PreferenciasActivity.EXTRA_RETURN_CONSULTA);
        if (requestCode == CONSULTA_MAPA_SALARIO_REQUEST && resultCode == RESULT_OK)
            consultaMapaSalario = data.getStringExtra(PreferenciasActivity.EXTRA_RETURN_CONSULTA);
    }

    public void enviarConsulta(View view) {
        Intent intent = new Intent(this, ResultadosActivity.class);
        if (!consultaHistogramaSalario.isEmpty())
            intent.putExtra(String.valueOf(CONSULTA_HISTOGRAMA_SALARIO_REQUEST), consultaHistogramaSalario);
        if (!consultaTendenciaSalario.isEmpty())
            intent.putExtra(String.valueOf(CONSULTA_TENDENCIA_SALARIO_REQUEST), consultaTendenciaSalario);
        if (!consultaMapaSalario.isEmpty())
            intent.putExtra(String.valueOf(CONSULTA_MAPA_SALARIO_REQUEST), consultaMapaSalario);
        intent.putExtra(CamposProgramas.EXTRA_POSICIÓN, posición);
        startActivity(intent);
    }

    private void terminarActivity(MaterialToolbar materialToolbar) {
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultasActivity.this.finish();
            }
        });
    }

    private void obtenerCampoDetallado() {
        Intent intent = getIntent();
        posición = intent.getIntExtra(CamposProgramas.EXTRA_POSICIÓN, 0);
    }


}