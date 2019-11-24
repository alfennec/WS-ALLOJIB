package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUrlCategoryPlat {

    public boolean result_succes = false;
    public boolean result_error = false;

    public JsonUrlCategoryPlat(String link , final Context ctx)
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
                CategoryPlat json_categoryPlat = new CategoryPlat();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_categoryPlat.id      = Integer.parseInt(oneObject.getString("id"));
                    json_categoryPlat.intituler   = oneObject.getString("intituler");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                CategoryPlatRepository.list_categoryPlat.add(json_categoryPlat);

            }
        }
        catch (Exception e)
        {

        }
    }
}
