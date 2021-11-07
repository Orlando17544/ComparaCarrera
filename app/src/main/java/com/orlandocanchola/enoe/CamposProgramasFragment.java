package com.orlandocanchola.enoe;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CamposProgramasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CamposProgramasFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "camposProgramasArray";
    private static CamposProgramas[] camposProgramas;

    public CamposProgramasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param camposProgramas Parameter 1.
     * @return A new instance of fragment CamposAmpliosFragment.
     */

    public static CamposProgramasFragment newInstance(CamposProgramas[] camposProgramas) {
        CamposProgramasFragment fragment = new CamposProgramasFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ARG_PARAM1, camposProgramas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            camposProgramas = (CamposProgramas[]) getArguments().getParcelableArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_campos_amplios, container, false);

        CamposProgramas[] camposProgramas = obtenerCamposProgramas(getArguments());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        personalizarRecyclerView(recyclerView, camposProgramas);

        return view;
    }

    private CamposProgramas[] obtenerCamposProgramas(Bundle args) {
        return (CamposProgramas[]) args.getParcelableArray(ARG_PARAM1);
    }

    private void personalizarRecyclerView(RecyclerView recyclerView, CamposProgramas[] camposProgramas) {
        RecyclerViewPersonalizado recyclerViewPersonalizado = new RecyclerViewPersonalizado(recyclerView, camposProgramas, getContext());
    }
}