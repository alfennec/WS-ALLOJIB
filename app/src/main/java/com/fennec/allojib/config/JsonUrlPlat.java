package com.fennec.allojib.config;

import android.content.Context;

import com.fennec.allojib.entity.CategoryPlat;
import com.fennec.allojib.entity.Plat;
import com.fennec.allojib.repository.CategoryPlatRepository;
import com.fennec.allojib.repository.PlatRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlPlat {

    public boolean result_succes = false;
    public boolean result_error = false;

    public JsonUrlPlat(String link , final Context ctx)
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
                Plat json_plat = new Plat();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_plat.id       = Integer.parseInt(oneObject.getString("id"));
                    json_plat.id_rest  = Integer.parseInt(oneObject.getString("id_rest"));
                    json_plat.id_cat   = Integer.parseInt(oneObject.getString("id_cat"));
                    json_plat.intituler= oneObject.getString("intituler");
                    json_plat.contenue = oneObject.getString("contenue");
                    json_plat.prix     = Float.parseFloat(oneObject.getString("prix"));
                    json_plat.accom    = Integer.parseInt(oneObject.getString("accom"));
                    json_plat.img      = oneObject.getString("img");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                PlatRepository.list_plat.add(json_plat);
            }
        }
        catch (Exception e)
        {

        }
    }
}
