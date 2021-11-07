package com.orlandocanchola.enoe;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import static java.lang.reflect.Array.getLength;

public class CamposProgramasRecyclerAdapter extends RecyclerView.Adapter {
    private CamposProgramas[] camposProgramasList;

    public CamposProgramasRecyclerAdapter(CamposProgramas[] camposProgramasList) {
        this.camposProgramasList = camposProgramasList;
    }

    @Override
    public int getItemViewType(int position) { //Regresa el tipo de vista(view type) deacuerdo con la evaluación de las expresiones regulares.
        Pattern p1 = Pattern.compile("^\\b\\d{2}\\b");//?<! es negativelookbehind
        Matcher m1 = p1.matcher(camposProgramasList[position].getNombre());
        Pattern p2 = Pattern.compile("^\\b\\d{3}\\b");//?<! es negativelookbehind
        Matcher m2 = p2.matcher(camposProgramasList[position].getNombre());
        Pattern p3 = Pattern.compile("^\\b\\d{5}\\b");//?<! es negativelookbehind
        Matcher m3 = p3.matcher(camposProgramasList[position].getNombre());
        if (m1.find()) {
            return 0;
        } else if (m2.find()) {
            return 1;
        } else if (m3.find()) {
            return 2;
        } else {
            return 3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.campos_especificos_item, parent, false);
            return new ViewHolderCampo(view);
        } else if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.campos_detallados_item, parent, false);
            return new ViewHolderCampo(view);
        } else if (viewType == 2) {
            view = layoutInflater.inflate(R.layout.campos_unitarios_item, parent, false);
            return new ViewHolderCampo(view);
        } else {
            view = layoutInflater.inflate(R.layout.programas_estudio_item, parent, false);
            return new ViewHolderPrograma(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pattern p1 = Pattern.compile("^\\b\\d{2}\\b\\s");
        Matcher m1 = p1.matcher(camposProgramasList[position].getNombre());
        Pattern p2 = Pattern.compile("^\\b\\d{3}\\b\\s");
        Matcher m2 = p2.matcher(camposProgramasList[position].getNombre());
        Pattern p3 = Pattern.compile("^\\b\\d{5}\\b\\s");
        Matcher m3 = p3.matcher(camposProgramasList[position].getNombre());
        Pattern p4 = Pattern.compile("^•\\s");
        Matcher m4 = p4.matcher(camposProgramasList[position].getNombre());

        if(m1.find()) {
            ViewHolderCampo viewHolderCampo = (ViewHolderCampo) holder;
            viewHolderCampo.materialTextView.setText(m1.replaceAll(""));
        } else if (m2.find()) {
            ViewHolderCampo viewHolderCampo = (ViewHolderCampo) holder;
            viewHolderCampo.materialTextView.setText(m2.replaceAll(""));
        } else if (m3.find()) {
            ViewHolderCampo viewHolderCampo = (ViewHolderCampo) holder;
            viewHolderCampo.materialTextView.setText(m3.replaceAll(""));
        } else {
            ViewHolderPrograma viewHolderPrograma = (ViewHolderPrograma) holder;
            viewHolderPrograma.programaEstudio.setText(m4.replaceAll(""));
            viewHolderPrograma.campoDetallado.setText(camposProgramasList[position].getCampoDetalladoNombre());
        }
    }

    @Override
    public int getItemCount() {
        return getLength(camposProgramasList);
    }

    class ViewHolderCampo extends RecyclerView.ViewHolder { //Este ViewHolder es solamente para campos, estos no se pueden clickar.
        MaterialTextView materialTextView;

        public ViewHolderCampo(@NonNull View itemView) {
            super(itemView);

            materialTextView = itemView.findViewById(R.id.campo);
        }
    }

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
            int posición = camposProgramasList[getAdapterPosition()].getId();

            Intent consultasCarrera = new Intent(context, ConsultasActivity.class);
            consultasCarrera.putExtra(CamposProgramas.EXTRA_POSICIÓN, posición);
            context.startActivity(consultasCarrera);
        }
    }
}