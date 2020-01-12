package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;


import com.fennec.allojib.entity.Product;
import com.fennec.allojib.repository.ProductRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlProduct {
    public boolean result_succes = false;
    public boolean result_error = false;

    public JsonUrlProduct(String link , final Context ctx)
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
                            Log.d("TAG_PRODUCT", "1 - GET FROM SERVER : " + result);
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
            Log.d("TAG_JSON", "onClick: succes " + result);
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
                Product json_product = new Product();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_product.id       = Integer.parseInt(oneObject.getString("id"));
                    json_product.id_mark  = Integer.parseInt(oneObject.getString("id_mark"));
                    json_product.id_cat   = Integer.parseInt(oneObject.getString("id_cat"));
                    json_product.intituler= oneObject.getString("intituler");
                    json_product.des = oneObject.getString("des");
                    json_product.prix     = Float.parseFloat(oneObject.getString("prix"));
                    json_product.img      = oneObject.getString("img");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                ProductRepository.list_product.add(json_product);
            }
        }
        catch (Exception e)
        {

        }
    }
}
