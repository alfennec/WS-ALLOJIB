package com.fennec.allojib.controller.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fennec.allojib.R;
import com.fennec.allojib.controller.CoursierActivity;
import com.fennec.allojib.controller.MapsActivity2;
import com.fennec.allojib.controller.Market_Activity;
import com.fennec.allojib.controller.Restaurant_Activity;
import com.fennec.allojib.controller.ui.profile.ProfileFragment;
import com.fennec.allojib.controller.ui.restaurant.RestaurantFragment;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public static HomeFragment main;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        main = this;


        //final TextView textView = root.findViewById(R.id.text_home);

        final Button bt_restaurant = (Button) root.findViewById(R.id.bt_restaurant);

        final Button bt_supermarcher = (Button) root.findViewById(R.id.bt_supermarcher);

        final Button bt_coursier = (Button) root.findViewById(R.id.bt_coursier);

        bt_restaurant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(inflater.getContext(), Restaurant_Activity.class);
                startActivity(intent);
            }
        });

        bt_supermarcher.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Costum_toast("On Construction ");

                Intent intent = new Intent(inflater.getContext(), Market_Activity.class);
                startActivity(intent);
            }
        });

        bt_coursier.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Costum_toast("On Construction ");

                Intent intent = new Intent(inflater.getContext(), CoursierActivity.class);
                startActivity(intent);
            }
        });

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    public static void Costum_toast(String msg)
    {
        /** Costume toast to test**/
        LayoutInflater inflater = main.getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_costum_toast, (ViewGroup) main.getActivity().findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(main.getActivity().getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        /** end teaosot **/
    }
}