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

    @Override
    public void onSucces(Object obj)
    {
        String result = obj.toString().replaceAll("\\s","");

        try
        {
            if(Integer.parseInt(result) == 406 )
            {
                MainActivity.OnFailedLogin();
            }else
                {
                    MainActivity.OnFailedLogin();
                }
        }catch (Exception e)
        {
            getJsonClient(obj.toString());
            MainActivity.OnSuccesLogin();
        }



    }

    @Override
    public void onFailed(Object obj)
    {
       //MainActivity.OnFailedLogin();
        Log.d("TAG_JSON", "onClick: SEND URL on failed ");
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
                        try {

                            Log.d("TAG_JSON", "onClick: SEND URL before if " + result + " the exepction : "+e);
                            if(result != null)
                            {
                                Log.d("TAG_JSON", "onClick: SEND URLin if " + result +"LPR");
                                onSucces(result);
                            }else
                            {
                                Log.d("TAG_JSON", "onClick: SEND URLin else " + result);
                                onFailed(-1);
                            }

                        }catch (Exception ex)
                        {
                            Log.e("TAG_JSON", "problème :  " + ex);
                        }

                    }
                });
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
                    json_client.sexe    = Integer.parseInt(oneObject.getString("sexe"));

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
