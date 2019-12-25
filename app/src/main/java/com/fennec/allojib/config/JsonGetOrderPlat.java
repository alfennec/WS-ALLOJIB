package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.OrderPlat;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.OrderPlatRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetOrderPlat implements IonHandler {

    public JsonGetOrderPlat(String link , final Context ctx)
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
                            ConditionResult(result);
                        }else
                        {
                            onFailed(406);
                        }
                    }
                });

    }

    @Override
    public void onSucces(Object obj)
    {
        /** parsing data **/
        ParseData(obj.toString());

        /** after write you code from  main **/
    }

    @Override
    public void onFailed(Object obj)
    {

    }

    public void ConditionResult(String result)
    {
        /*try
        {
            if(Integer.parseInt(result) == 406)
            {
                onFailed(result);
            }
            else{
                onSucces(result);
            }
        }catch (Exception e)
        {
            Log.d("TAG_exception", "ConditionResult: "+e);
        }*/

        onSucces(result);

    }

    public void ParseData(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);

            for (int i=0; i < jArray.length(); i++)
            {
                OrderPlat json_OrderPlat = new OrderPlat();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_OrderPlat.id           = Integer.parseInt(oneObject.getString("id"));
                    json_OrderPlat.id_passOrder = Integer.parseInt(oneObject.getString("id_order"));
                    json_OrderPlat.id_plat      = Integer.parseInt(oneObject.getString("id_plat"));
                    json_OrderPlat.quantity     = Integer.parseInt(oneObject.getString("quatity"));
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                OrderPlatRepository.list_orderPlat.add(json_OrderPlat);
                //Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ json_OrderPlat.quantity);
            }
        }
        catch (Exception e)
        {

        }
    }
}

