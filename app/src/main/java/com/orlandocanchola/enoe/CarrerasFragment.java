package com.orlandocanchola.enoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CarrerasFragment extends Fragment {

    MaterialToolbar materialToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carreras, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);

        añadirAdapterAViewPager2(viewPager2);

        enlazarViewPager2ATabLayout(view, viewPager2);

        cambiarIconoMaterialToolbar();

        crearBuscarActivity();

        return view;
    }

    private void añadirAdapterAViewPager2(ViewPager2 viewPager2) {
        viewPager2.setAdapter(new CamposAmpliosPagerAdapter(getActivity()));
    }

    private void enlazarViewPager2ATabLayout(View view, ViewPager2 viewPager2) {
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position) {
                    case 0:
                        tab.setText("Educación");
                        break;
                    case 1:
                        tab.setText("Artes y Humanidades");
                        break;
                    case 2:
                        tab.setText("Ciencias Sociales, Administración y Derecho");
                        break;
                    case 3:
                        tab.setText("Ciencias Naturales, Exactas y de la Computación");
                        break;
                    case 4:
                        tab.setText("Ingeniería, Manufactura y Construcción");
                        break;
                    case 5:
                        tab.setText("Agronomía y Veterinaria");
                        break;
                    case 6:
                        tab.setText("Salud");
                        break;
                    case 7:
                        tab.setText("Servicios");
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }

    private void cambiarIconoMaterialToolbar() {
        materialToolbar = getActivity().findViewById(R.id.materialToolbar);
        materialToolbar.getMenu().clear(); //Quito todos los items del menu
        materialToolbar.inflateMenu(R.menu.carreras_fragment_toolbar); //Agrega un menu al toolbar
    }

    private void crearBuscarActivity() {
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.buscarCarrera:
                        Intent intent = new Intent(getActivity(), BuscarCarreraActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
    }
}
