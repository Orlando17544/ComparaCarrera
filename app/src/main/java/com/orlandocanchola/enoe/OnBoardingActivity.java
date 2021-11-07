package com.orlandocanchola.enoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout onBoardingIndicatorLayout;
    private MaterialButton onBoardingButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        sharedPreferences = getSharedPreferences(FavoritosFragment.NAME_FILE, MODE_PRIVATE);

        if (!sharedPreferences.getBoolean("primeraVez", true)) { //Ya tuvo su primera vez
            Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        onBoardingIndicatorLayout = findViewById(R.id.onBoardingIndicators);
        onBoardingButton = findViewById(R.id.onBoardingButton);

        setUpOnBoardingItems();
        ViewPager2 onBoardingViewPager2 = findViewById(R.id.onBoardingViewPager2);
        onBoardingViewPager2.setAdapter(onBoardingAdapter);

        setUpOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);

        onBoardingViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

        onBoardingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBoardingViewPager2.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    onBoardingViewPager2.setCurrentItem(onBoardingViewPager2.getCurrentItem() + 1);
                } else {
                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putBoolean("primeraVez", false);
                    preferencesEditor.apply();
                    Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setUpOnBoardingItems() {
        List<OnBoardingItem> onBoardingItemList = new ArrayList<>();

        OnBoardingItem analizaItem = new OnBoardingItem();
        analizaItem.setTitle("Analiza el mercado laboral");
        analizaItem.setDescription("Analiza los salarios de acuerdo a tus circunstancias.");
        analizaItem.setImage(R.drawable.analiza);

        OnBoardingItem comparaItem = new OnBoardingItem();
        comparaItem.setTitle("Compara carreras");
        comparaItem.setDescription("Compara las carreras de acuerdo a los salarios.");
        comparaItem.setImage(R.drawable.compara);

        OnBoardingItem exploraItem = new OnBoardingItem();
        exploraItem.setTitle("Explora las carreras profesionales");
        exploraItem.setDescription("Explora carreras que desconocías y que podrían ser una opción alternativa.");
        exploraItem.setImage(R.drawable.explora);

        onBoardingItemList.add(analizaItem);
        onBoardingItemList.add(comparaItem);
        onBoardingItemList.add(exploraItem);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemList);
    }

    private void setUpOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            onBoardingIndicatorLayout.addView(indicators[i]);
        }
    }

    public void setCurrentOnBoardingIndicator(int index) {
        int childCount = onBoardingIndicatorLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) onBoardingIndicatorLayout.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable. onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onBoardingAdapter.getItemCount() - 1) {
            onBoardingButton.setText("Empezar");
        } else {
            onBoardingButton.setText("Siguiente");
        }
    }
}