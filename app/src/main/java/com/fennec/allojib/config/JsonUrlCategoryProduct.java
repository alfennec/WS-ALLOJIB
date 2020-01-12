package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.CategoryProduct;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.CategoryProductRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlCategoryProduct implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;


    @Override
    public void onSucces(Object obj)
    {
        //Restaurant_Activity.onLoadCategory();
    }

    @Override
    public void onFailed(Object obj)
    {

    }


    public JsonUrlCategoryProduct(String link , final Context ctx)
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
                            Log.d("TAG_PRODUCT", "3 - GET FROM SERVER CATEGORIE : " + result);
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
            onSucces(result.toString());
        }
    }

    public void parse_data(String result)
    {

        CategoryProductRepository.list_categoryProduct.add(new CategoryProduct(0,"Tout"));

        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);


            for (int i=0; i < jArray.length(); i++)
            {
                CategoryProduct json_categoryProduct = new CategoryProduct();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_categoryProduct.id        = Integer.parseInt(oneObject.getString("id"));
                    json_categoryProduct.intituler = oneObject.getString("intituler");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                CategoryProductRepository.list_categoryProduct.add(json_categoryProduct);

            }
        }
        catch (Exception e)
        {

        }
    }
}
