package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.controller.Home_Activity;
import com.fennec.allojib.controller.ui.commande.CommandeFragment;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.entity.PassOrderPlat;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetPassOrderPlat implements IonHandler {

    public int choice_activity;

    public JsonGetPassOrderPlat(String link , final Context ctx , int choice_activity)
    {
        this.choice_activity = choice_activity;

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

        if(choice_activity == 1)
        {
            Home_Activity.OnJsonSucces();
        }else
            {
                CommandeFragment.onSucces();
            }

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
                PassOrderPlat json_passOrderPlat = new PassOrderPlat();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_passOrderPlat.id = Integer.parseInt(oneObject.getString("id"));
                    json_passOrderPlat.total = Double.parseDouble(oneObject.getString("total"));
                    json_passOrderPlat.mode_livraison   = Integer.parseInt(oneObject.getString("mode_livraison"));
                    json_passOrderPlat.date_order   = oneObject.getString("date_order");
                    json_passOrderPlat.time_order   = oneObject.getString("time_order");
                    json_passOrderPlat.collecteur   = Integer.parseInt(oneObject.getString("collecteur"));
                    json_passOrderPlat.nom_collecteur   = oneObject.getString("nom_collecteur");
                    json_passOrderPlat.num_collecteur   = oneObject.getString("num_collecteur");
                    json_passOrderPlat.adresse   = oneObject.getString("adresse");
                    json_passOrderPlat.note   = oneObject.getString("note");
                    json_passOrderPlat.id_client   = Integer.parseInt(oneObject.getString("id_client"));
                    json_passOrderPlat.situation   = Integer.parseInt(oneObject.getString("situation"));

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                PassOrderPlatRepository.list_passOrderPlat.add(json_passOrderPlat);
            }
        }
        catch (Exception e)
        {

        }
    }
}
