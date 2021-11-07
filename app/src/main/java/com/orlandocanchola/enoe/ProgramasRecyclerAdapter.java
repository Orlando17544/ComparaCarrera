package com.orlandocanchola.enoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ProgramasRecyclerAdapter extends RecyclerView.Adapter implements Filterable {
    private ArrayList<CamposProgramas> programasList;

     public ProgramasRecyclerAdapter(ArrayList<CamposProgramas> programasList) {
        this.programasList = programasList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        view = layoutInflater.inflate(R.layout.programas_estudio_item, parent, false);
        return new ViewHolderPrograma(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pattern p = Pattern.compile("^•\\s");
        Matcher m = p.matcher(programasList.get(position).getNombre());

        ViewHolderPrograma viewHolderPrograma = (ViewHolderPrograma) holder;
        viewHolderPrograma.programaEstudio.setText(m.replaceAll(""));
        viewHolderPrograma.campoDetallado.setText(programasList.get(position).getCampoDetalladoNombre());
    }

    @Override
    public int getItemCount() {
        return programasList.size();
    }

    @Override
    public Filter getFilter() {
        return programasFilter;
    }

    private Filter programasFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CamposProgramas> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {

                filteredList.addAll(CamposProgramas.getProgramas());

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                filteredList.addAll(CamposProgramas.filtrarProgramas(filterPattern));
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            programasList.clear();
            programasList.addAll((Collection<? extends CamposProgramas>) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolderPrograma extends RecyclerView.ViewHolder implements View.OnClickListener { //Este ViewHolder es para Programas, este si se puede clickar.
        MaterialTextView programaEstudio;
        MaterialTextView campoDetallado;
        private final Context context;

        public ViewHolderPrograma(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            programaEstudio = itemView.findViewById(R.id.programa);
            campoDetallado = itemView.findViewById(R.id.campoDetallado);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int posición = programasList.get(getAdapterPosition()).getId();

            if (BuscarCarreraActivity.procedencia != null && BuscarCarreraActivity.procedencia.equals("favorito")) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(FavoritosFragment.NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                preferencesEditor.putInt("key" + posición, posición);
                preferencesEditor.apply();
                ((BuscarCarreraActivity) context).finish();
                BuscarCarreraActivity.procedencia = "";
            } else {
                Intent consultasCarrera = new Intent(context, ConsultasActivity.class);
                consultasCarrera.putExtra(CamposProgramas.EXTRA_POSICIÓN, posición);
                context.startActivity(consultasCarrera);
            }
        }
    }
}
