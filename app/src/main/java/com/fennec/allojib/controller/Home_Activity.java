package com.fennec.allojib.controller;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonGetOrderPlat;
import com.fennec.allojib.config.JsonGetPassOrderPlat;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlPassOrderPlat;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.ui.profile.ProfileFragment;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static Home_Activity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        main = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_panier, R.id.nav_commande, R.id.nav_profile,
                R.id.nav_quitter)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                if(destination.getId() == R.id.nav_quitter)
                {
                    //main.finish();
                    //Toast.makeText(Home_Activity.this, "hhhhhhhhhh", Toast.LENGTH_LONG).show();
                    //Log.d("TAG_CLICK", "onDestinationChanged: ");

                    Home_Activity.this.finish();

                }
            }
        });


        /** get ALL DATA YOU NEED HERE **************************************************************/

        /** clear data first **/
        RestaurantRepository.list_restaurant.clear();
        PlatRepository.list_plat.clear();
        CategoryPlatRepository.list_categoryPlat.clear();
        OrderPlatRepository.list_orderPlat.clear();
        PassOrderPlatRepository.list_passOrderPlat.clear();

        /** get PASS ORDER **/
        //localhost/livraison/json/getPassOrderPlat.php?id_client=1
        String url_informations = constant.url_host+"json/getPassOrderPlat.php?";
        String id_client = "id_client="+ ClientRepository.main_Client.id;
        url_informations = url_informations+id_client;
        JsonGetPassOrderPlat jsonGetPassOrderPlat = new JsonGetPassOrderPlat(url_informations, main);

        /** get RESTAURANT & CATEGORIE PLAT **/
        url_informations = constant.url_host+"/json/getTable.php?table=";
        JsonUrlPlat jsonUrlPlat = new JsonUrlPlat(url_informations+"tbl_plat", main);
        JsonUrlRestaurant jsonRestaurant = new JsonUrlRestaurant(url_informations+"tbl_retaurant", main);
        JsonUrlCategoryPlat jsonUrlCategoryPlat = new JsonUrlCategoryPlat(url_informations+"tbl_category_plat", main);
    }

    public static void OnJsonSucces()
    {
        //localhost/livraison/json/getOrderPlat.php?id_order=17



        for (int i = 0; i < PassOrderPlatRepository.list_passOrderPlat.size(); i++)
        {
            String url_informations = constant.url_host+"json/getOrderPlat.php?";

            String id_order = "id_order="+ PassOrderPlatRepository.list_passOrderPlat.get(i).id;

            url_informations = url_informations+id_order;

            Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ url_informations);
            Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ id_order);

            JsonGetOrderPlat jsonGetOrderPlat = new JsonGetOrderPlat(url_informations, main);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.home_, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
