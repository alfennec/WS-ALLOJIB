package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.controller.MainActivity;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.ClientRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUrlClient implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;

    @Override
    public void onSucces(Object obj)
    {
        ConditionResult(obj.toString());

        Log.d("TAG_JSON", "onClick: SEND URL " + obj.toString());

        if(!result_error)
        {
            MainActivity.OnSuccesLogin();
        }else {
                MainActivity.OnFailedLogin();
        }

    }

    @Override
    public void onFailed(Object obj)
    {

    }

    public JsonUrlClient(String link , final Context ctx)
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
                            onSucces( result );

                        }else
                            {
                                onFailed(-1);
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
            getJsonClient(result);
        }
    }

    public void getJsonClient(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);

            for (int i=0; i < jArray.length(); i++)
            {
                Client json_client = new Client();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_client.id      = Integer.parseInt(oneObject.getString("id"));
                    json_client.email   = oneObject.getString("email");
                    json_client.pass    = oneObject.getString("pass");
                    json_client.nom     = oneObject.getString("nom");
                    json_client.prenom  = oneObject.getString("prenom");
                    json_client.tel     = oneObject.getString("tel");
                    json_client.adresse = oneObject.getString("adresse");
                    json_client.ville   = oneObject.getString("ville");
                    json_client.sexe    = Integer.parseInt(oneObject.getString("pass"));

                }
                catch (JSONException e)
                {
                        //Log.e("tag_json", ""+e);
                }

                ClientRepository.main_Client = json_client;
            }
        }
        catch (Exception e)
        {

        }
    }
}
