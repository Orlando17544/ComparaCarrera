package com.orlandocanchola.enoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class ResultadosActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private int posición;
    private MaterialToolbar materialToolbar;
    private Intent intent;
    private boolean favorito = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        obtenerSharedPreferences();

        obtenerPosición();

        obtenerMaterialToolbar();

        if (elementoEnSharedPreferences())
            cambiarColorIconoFavoritos();

        favoritos();

        configurarWebView();

        terminarResultadosActivity();
    }

    private void favoritos() {
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.agregarCarrera:
                        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                        if (favorito) {
                            eliminarElemento(preferencesEditor);
                        } else {
                            agregarElemento(preferencesEditor);
                        }
                }
                return true;
            }
        });
    }

    private boolean elementoEnSharedPreferences() {
        favorito = sharedPreferences.contains("key" + posición);
        return favorito;
    }

    private void cambiarColorIconoFavoritos() {
        if (favorito)
            materialToolbar.getMenu().getItem(0).getIcon().setTint(Color.YELLOW);
        else
            materialToolbar.getMenu().getItem(0).getIcon().setTint(Color.WHITE);
    }

    private void obtenerPosición() {
        intent = getIntent();
        posición = intent.getIntExtra(CamposProgramas.EXTRA_POSICIÓN, 0);
    }

    private void obtenerSharedPreferences() {
        sharedPreferences = getSharedPreferences(FavoritosFragment.NAME_FILE, MODE_PRIVATE);
    }

    private String obtenerCampoDetallado() {
        String campoDetallado = CamposProgramas.getCamposProgramas()[posición].getCampoDetalladoNombre();
        return campoDetallado;
    }

    private String obtenerConsultaHistogramaSalario() {
        String consultaHistogramaSalario = intent.getStringExtra(String.valueOf(ConsultasActivity.CONSULTA_HISTOGRAMA_SALARIO_REQUEST));
        return consultaHistogramaSalario;
    }

    private String obtenerConsultaTendenciaSalario() {
        String consultaTendenciaSalario = intent.getStringExtra(String.valueOf(ConsultasActivity.CONSULTA_TENDENCIA_SALARIO_REQUEST));
        return consultaTendenciaSalario;
    }

    private String obtenerConsultaMapaSalario() {
        String consultaMapaSalario = intent.getStringExtra(String.valueOf(ConsultasActivity.CONSULTA_MAPA_SALARIO_REQUEST));
        return consultaMapaSalario;
    }

    private void configurarWebView() {
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(obtenerCampoDetallado(), obtenerConsultaHistogramaSalario(), obtenerConsultaTendenciaSalario(), obtenerConsultaMapaSalario()), "Android");
        webView.loadUrl("file:///android_asset/pagina.html");
    }

    private void terminarResultadosActivity() {
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultadosActivity.this.finish();
            }
        });
    }

    private void agregarElemento(SharedPreferences.Editor preferencesEditor) {
        favorito = !favorito; //Cambiado a true
        preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putInt("key" + posición, posición);
        preferencesEditor.apply();
        cambiarColorIconoFavoritos();
    }

    private void eliminarElemento(SharedPreferences.Editor preferencesEditor) {
        favorito = !favorito; //Cambialo a false
        preferencesEditor.remove("key" + posición);
        preferencesEditor.apply();
        cambiarColorIconoFavoritos();
    }

    private void obtenerMaterialToolbar() {
        materialToolbar = (MaterialToolbar) findViewById(R.id.materialToolbar);
    }
}