package com.orlandocanchola.enoe;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class CamposAmpliosPagerAdapter extends FragmentStateAdapter {

    public CamposAmpliosPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public CamposProgramasFragment createFragment(int position) { //Dependiendo de la posición de la pantalla regresa un fragment.
        CamposProgramas[] camposProgramasArray;
        switch(position) {
            case 0:
                camposProgramasArray = CamposProgramas.getCamposProgramasEducación();
                break;
            case 1:
                camposProgramasArray = CamposProgramas.getCamposProgramasArtesHumanidades();
                break;
            case 2:
                camposProgramasArray = CamposProgramas.getCamposProgramasCienciasSocialesAdministraciónDerecho();
                break;
            case 3:
                camposProgramasArray = CamposProgramas.getCamposProgramasCienciasNaturalesExactasComputación();
                break;
            case 4:
                camposProgramasArray = CamposProgramas.getCamposProgramasIngenieríaManufacturaConstrucción();
                break;
            case 5:
                camposProgramasArray = CamposProgramas.getCamposProgramasAgronomíaVeterinaria();
                break;
            case 6:
                camposProgramasArray = CamposProgramas.getCamposProgramasSalud();
                break;
            default:
                camposProgramasArray = CamposProgramas.getCamposProgramasServicios();
                break;
        }
        return CamposProgramasFragment.newInstance(camposProgramasArray); //Crea un fragmento apartir de un arrayList de objetos
    }

    @Override
    public int getItemCount() {
        return 8;
    } //Número de fragments.
}
