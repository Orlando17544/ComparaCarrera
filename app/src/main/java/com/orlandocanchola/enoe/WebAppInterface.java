package com.orlandocanchola.enoe;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WebAppInterface {
    String campoDetallado;
    String consultaHistogramaSalario;
    String consultaTendenciaSalario;
    String consultaMapaSalario;

    public WebAppInterface(String campoDetallado, String consultaHistogramaSalario, String consultaTendenciaSalario, String consultaMapaSalario) {
        this.campoDetallado = campoDetallado;
        this.consultaHistogramaSalario = consultaHistogramaSalario;
        this.consultaTendenciaSalario = consultaTendenciaSalario;
        this.consultaMapaSalario = consultaMapaSalario;
    }

    @JavascriptInterface
    public String getConsultaHistogramaSalario() {
        return consultaHistogramaSalario;
    }

    @JavascriptInterface
    public String getConsultaTendenciaSalario() {
        return consultaTendenciaSalario;
    }

    @JavascriptInterface
    public String getConsultaMapaSalario() {
        return consultaMapaSalario;
    }

    @JavascriptInterface
    public String getCampoDetallado() {
        return campoDetallado;
    }
}
