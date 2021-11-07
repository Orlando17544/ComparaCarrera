package com.orlandocanchola.enoe;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

public class AcercaFragment extends Fragment {

    private MaterialToolbar materialToolbar;
    private static final String MENSAJE_COMPARTIR = "Quiero que conozcas esta aplicación, me gustó mucho!!!.";
    private static final String TÍTULO_CHOOSER = "Elige una aplicación.";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acerca, container, false);

        cambiarIconoMaterialToolbar();

        menuCompartir();

        return view;
    }

    private void cambiarIconoMaterialToolbar() {
        materialToolbar = getActivity().findViewById(R.id.materialToolbar);
        materialToolbar.getMenu().clear(); //Quito todos los items del menu
        materialToolbar.inflateMenu(R.menu.acerca_fragment_toolbar); //Agrega un menu al toolbar
    }

    private void menuCompartir() {
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.compartir:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, MENSAJE_COMPARTIR);
                        intent.setType("text/plain");
                        Intent chooser = crearChooser(intent);
                        enviarIntentImplicito(intent, chooser);
                }
                return true;
            }
        });
    }

    private void enviarIntentImplicito(Intent intent, Intent chooser) {
        if (aplicacionesDisponibles(intent) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getContext(), "No se encontró una aplicación con la cual compartir.", Toast.LENGTH_LONG).show();
        }
    }

    private ComponentName aplicacionesDisponibles(Intent intent) {
        return intent.resolveActivity(getActivity().getPackageManager());
    }

    private Intent crearChooser(Intent intent) {
        Intent chooser = Intent.createChooser(intent, TÍTULO_CHOOSER);
        return chooser;
    }
}
