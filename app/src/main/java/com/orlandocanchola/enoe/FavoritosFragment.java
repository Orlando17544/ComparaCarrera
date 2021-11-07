package com.orlandocanchola.enoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Map;

import static com.orlandocanchola.enoe.R.menu.favoritos_fragment_toolbar;

public class FavoritosFragment extends Fragment {

    private Map<String, ?> favoritos;
    private RecyclerViewPersonalizado recyclerViewPersonalizado;
    private String EXTRA_CLAVE = "com.orlandocanchola.enoe";

    private View view;

    private MaterialToolbar materialToolbar;

    public static final String NAME_FILE = "com.orlandocanchola.enoe";
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        clickAgregarCarrera();

        cambiarIconoMaterialToolbar();

        agregarCarreraMenu();

        return view;
    }


    private void personalizarRecyclerView(RecyclerView recyclerView) {
        recyclerViewPersonalizado = new RecyclerViewPersonalizado(recyclerView, CamposProgramas.getProgramasFavoritos(favoritos), getContext());
    }

    private void cambiarIconoMaterialToolbar() {
        materialToolbar = getActivity().findViewById(R.id.materialToolbar);

        materialToolbar.getMenu().clear(); //Quito todos los items del menu
        materialToolbar.inflateMenu(favoritos_fragment_toolbar); //Agrega un menu al toolbar
    }

    @Override
    public void onStart() { //Actualizo las shared preferences
        super.onStart();
        sharedPreferences = getActivity().getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE);

        favoritos = sharedPreferences.getAll();

        favoritos.remove("primeraVez");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView textView = view.findViewById(R.id.textView);
        ImageButton imageButton = view.findViewById(R.id.agregarCarrera);

        if (favoritos.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            imageButton.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            personalizarRecyclerView(recyclerView);
        }
    }

    private void clickAgregarCarrera() {
        ImageButton agregarCarrera = view.findViewById(R.id.agregarCarrera);

        agregarCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuscarCarreraActivity.class);
                intent.putExtra(BuscarCarreraActivity.EXTRA_CLAVE, "favorito");
                startActivity(intent);
            }
        });
    }

    private void agregarCarreraMenu() {
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.añadirCarrera:
                        Intent intent = new Intent(getActivity(), BuscarCarreraActivity.class);
                        intent.putExtra(BuscarCarreraActivity.EXTRA_CLAVE, "favorito");
                        startActivity(intent);
                }
                return true;
            }
        });
    }
}
