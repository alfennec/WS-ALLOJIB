package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fennec.allojib.R;
import com.fennec.allojib.controller.ui.restaurant.RestaurantFragment;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RestaurantFragment.newInstance())
                    .commitNow();
        }
    }
}
