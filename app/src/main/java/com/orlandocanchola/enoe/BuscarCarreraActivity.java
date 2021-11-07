package com.orlandocanchola.enoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class BuscarCarreraActivity extends AppCompatActivity {

    private RecyclerViewPersonalizado recyclerViewPersonalizado;
    public static String procedencia = new String();
    public static final String EXTRA_CLAVE = "com.orlandocanchola.enoe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_carrera);

        obtenerProcedencia();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        personalizarRecyclerView(recyclerView);

        MaterialToolbar materialToolbar = findViewById(R.id.materialToolbar);
        terminarBuscarCarreraActivity(materialToolbar);

        SearchView searchView = (SearchView) materialToolbar.getMenu().findItem(R.id.action_search).getActionView();
        obtenerFocus(searchView);
        agregarHint(searchView);
        filtrarViews(searchView);
    }

    private void personalizarRecyclerView(RecyclerView recyclerView) {
        recyclerViewPersonalizado = new RecyclerViewPersonalizado(recyclerView, CamposProgramas.getProgramas(), this);
    }

    public void terminarBuscarCarreraActivity(MaterialToolbar materialToolbar) {
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarCarreraActivity.this.finish();
            }
        });
    }

    private void obtenerFocus(SearchView searchView) {
        searchView.setIconifiedByDefault(false); //Esto hace que se expanda por defecto, listo para que entre la busqueda
        searchView.requestFocus(); //Le damos focus a la vista.
    }

    private void agregarHint(SearchView searchView) {
        searchView.setQueryHint("Buscar carrera");
    }

    private void filtrarViews(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //Al presionar la tecla de buscar no hará nada.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //Al cambiar el texto que el usuario va escribiendo.
                recyclerViewPersonalizado.getProgramasAdapter().getFilter().filter(newText);
                return false;
            }
        });
    }

    public void obtenerProcedencia() {
        Intent intent = getIntent();
        procedencia = intent.getStringExtra(EXTRA_CLAVE);
    }
}
