package com.orlandocanchola.enoe;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewPersonalizado {

    private CamposProgramasRecyclerAdapter camposProgramasAdapter;
    private ProgramasRecyclerAdapter programasAdapter;

    public RecyclerViewPersonalizado(RecyclerView recyclerView, CamposProgramas[] camposProgramas, Context context) {
        agregarDividersRecyclerView(recyclerView, context);
        agregarAdaptadorRecyclerView(recyclerView, camposProgramas);
        agregarLayoutManagerRecyclerView(recyclerView, context);
    }

    public RecyclerViewPersonalizado(RecyclerView recyclerView, ArrayList<CamposProgramas> programas, Context context) {
        agregarDividersRecyclerView(recyclerView, context);
        agregarAdaptadorRecyclerView(recyclerView, programas);
        agregarLayoutManagerRecyclerView(recyclerView, context);
    }

    public ProgramasRecyclerAdapter getProgramasAdapter() {
        return programasAdapter;
    }

    private void agregarDividersRecyclerView(androidx.recyclerview.widget.RecyclerView recyclerView, Context context) {
        DividerItemDecoration dividerItemDecorationVertical = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        DividerItemDecoration dividerItemDecorationHorizontal = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);

        dividerItemDecorationHorizontal.setDrawable(context.getResources().getDrawable(R.drawable.recyclerview_divider));

        recyclerView.addItemDecoration(dividerItemDecorationVertical);
        recyclerView.addItemDecoration(dividerItemDecorationHorizontal);
    }

    private void agregarAdaptadorRecyclerView(RecyclerView recyclerView, CamposProgramas[] camposProgramas) {
        camposProgramasAdapter = new CamposProgramasRecyclerAdapter(camposProgramas);
        recyclerView.setAdapter(camposProgramasAdapter);
    }

    private void agregarAdaptadorRecyclerView(RecyclerView recyclerView, ArrayList<CamposProgramas> programas) {
        programasAdapter = new ProgramasRecyclerAdapter(programas);
        recyclerView.setAdapter(programasAdapter);
    }

    private void agregarLayoutManagerRecyclerView(androidx.recyclerview.widget.RecyclerView recyclerView, Context context) {
        androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
    }
}
