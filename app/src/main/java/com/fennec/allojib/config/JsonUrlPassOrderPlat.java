package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;



public class JsonUrlPassOrderPlat {

    public boolean result_succes = false;
    public boolean result_error = false;

    public int position;

    public JsonUrlPassOrderPlat(String link , final Context ctx, int position)
    {
        this.position = position;

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
                            Log.d("TAG_JSON_ORDER", "RESULT: SEND URL " + result);
                            ConditionResult( result );
                        }else
                            {
                                Log.d("TAG_JSON_ORDER", "Error: Result ");
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
            ParseData(result);
        }
    }

    public void ParseData(String result)
    {
        try
        {
            int id_passOrderPlat = Integer.parseInt(result);

            if(id_passOrderPlat % 2 == 1 || id_passOrderPlat % 2 == 0)
            {
                PassOrderPlatRepository.list_passOrderPlat.get(position).id = id_passOrderPlat;

                Log.d("TAG_JSON_ORDER", "Wééé: Result " + PassOrderPlatRepository.list_passOrderPlat.get(position).id+" "+PassOrderPlatRepository.list_passOrderPlat.get(position).nom_collecteur);
            }else
                {
                    Log.d("TAG_JSON_ORDER", "Error: Result " + result);
                }
        }
        catch (Exception e)
        {
            Log.d("TAG_JSON_ORDER", "Error: Result " + e);
        }
    }
}
