package com.fennec.allojib.controller;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.fennec.allojib.R;
import com.fennec.allojib.config.JsonGetOrderPlat;
import com.fennec.allojib.config.JsonGetOrderProduct;
import com.fennec.allojib.config.JsonGetPassOrderPlat;
import com.fennec.allojib.config.JsonGetPassOrderProduct;
import com.fennec.allojib.config.JsonUrlCategoryPlat;
import com.fennec.allojib.config.JsonUrlCategoryProduct;
import com.fennec.allojib.config.JsonUrlMarket;
import com.fennec.allojib.config.JsonUrlPassOrderPlat;
import com.fennec.allojib.config.JsonUrlPlat;
import com.fennec.allojib.config.JsonUrlProduct;
import com.fennec.allojib.config.JsonUrlRestaurant;
import com.fennec.allojib.config.constant;
import com.fennec.allojib.controller.ui.profile.ProfileFragment;
import com.fennec.allojib.entity.PassOrderProduct;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.CategoryProductRepository;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.MarketRepository;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.OrderProductRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PassOrderProductRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.ProductRepository;
import com.fennec.allojib.repository.RestaurantRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
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

    public static String MY_PREFS_NAME = "first_log";

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

                    MainActivity.quitter();
                    Home_Activity.this.finishAffinity();
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

        MarketRepository.list_market.clear();
        ProductRepository.list_product.clear();
        CategoryProductRepository.list_categoryProduct.clear();
        OrderProductRepository.list_orderProduct.clear();
        PassOrderProductRepository.list_passOrderProduct.clear();

        /** get PASS ORDER PLAT **/
        //localhost/livraison/json/getPassOrderPlat.php?id_client=1
        String url_informations = constant.url_host+"json/getPassOrderPlat.php?";
        String id_client = "id_client="+ ClientRepository.main_Client.id;
        url_informations = url_informations+id_client;
        JsonGetPassOrderPlat jsonGetPassOrderPlat = new JsonGetPassOrderPlat(url_informations, main,1);

        /** get PASS ORDER PRODUCT **/
        //localhost/livraison/json/getPassOrderPlat.php?id_client=1
        url_informations = constant.url_host+"json/getPassOrderProduct.php?";
        id_client = "id_client="+ ClientRepository.main_Client.id;
        url_informations = url_informations+id_client;
        JsonGetPassOrderProduct jsonGetPassOrderProduct = new JsonGetPassOrderProduct(url_informations, main,1);

        /** get RESTAURANT & CATEGORIE PLAT **/
        url_informations = constant.url_host+"/json/getTable.php?table=";
        JsonUrlPlat jsonUrlPlat = new JsonUrlPlat(url_informations+"tbl_plat", main);
        JsonUrlRestaurant jsonRestaurant = new JsonUrlRestaurant(url_informations+"tbl_retaurant", main);
        JsonUrlCategoryPlat jsonUrlCategoryPlat = new JsonUrlCategoryPlat(url_informations+"tbl_category_plat", main);

        /** get MARKET & CATEGORIE PRODUCT **/
        url_informations = constant.url_host+"/json/getTable.php?table=";
        JsonUrlProduct jsonUrlProduct = new JsonUrlProduct(url_informations+"tbl_product", main);
        JsonUrlMarket jsonMarket = new JsonUrlMarket(url_informations+"tbl_market", main);
        JsonUrlCategoryProduct jsonUrlCategoryProduct = new JsonUrlCategoryProduct(url_informations+"tbl_category_product", main);


        /*********************/


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

            JsonGetOrderPlat jsonGetOrderPlat = new JsonGetOrderPlat(url_informations, main,1);
        }
    }

    public static void OnJsonSucces2()
    {
        //localhost/livraison/json/getOrderProduct.php?id_order=17

        for (int i = 0; i < PassOrderProductRepository.list_passOrderProduct.size(); i++)
        {
            String url_informations = constant.url_host+"json/getOrderProduct.php?";

            String id_order = "id_order="+ PassOrderProductRepository.list_passOrderProduct.get(i).id;

            url_informations = url_informations+id_order;

            Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ url_informations);
            Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ id_order);

            JsonGetOrderProduct jsonGetOrderProduct = new JsonGetOrderProduct(url_informations, main,1);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                composeMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void composeMessage ()
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
        callIntent.setData(Uri.parse("tel:0611625432"));    //this is the phone number calling
        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        if (ActivityCompat.checkSelfPermission(main, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(main,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        }else {     //have got permission
            try{
                main.startActivity(callIntent);  //call activity and make phone call
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(main.getApplicationContext(),"une erreur c'est produite",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
