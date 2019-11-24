package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.Client;
import com.fennec.allojib.entity.Restaurant;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.fennec.allojib.repository.RestaurantRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlRestaurant {

    public boolean result_succes = false;
    public boolean result_error = false;

    public JsonUrlRestaurant(String link , final Context ctx)
    {
        Ion.with(ctx)
                .load(link)
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                        if(result != null)
                        {
                            //Log.d("TAG_JSON", "onClick: SEND URL " + result);
                            ConditionResult( result );
                        }
                    }
                });

    }

    public void ConditionResult(String result)
    {
        if(result.equals("succes"))
        {
            result_succes = true;
        }
        else if(result.equals("error"))
        {
            result_error = true;
        }
        else {
            result_succes = true;
            parse_data(result);
        }
    }

    public void parse_data(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);

            for (int i=0; i < jArray.length(); i++)
            {
                Restaurant json_restaurant = new Restaurant();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_restaurant.id      = Integer.parseInt(oneObject.getString("id"));
                    json_restaurant.intituler   = oneObject.getString("intituler");
                    json_restaurant.specialiter   = oneObject.getString("specialiter");
                    json_restaurant.situation   = Integer.parseInt(oneObject.getString("situation"));
                    json_restaurant.prix_transp   = Integer.parseInt(oneObject.getString("prix_transp"));
                    json_restaurant.restaurant_image   = oneObject.getString("restaurant_image");
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                RestaurantRepository.list_restaurant.add(json_restaurant);


            }
        }
        catch (Exception e)
        {

        }
    }
}
