package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.Client;
import com.fennec.allojib.repository.ClientRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_url {

    public int last_id=0;

    public Json_url(String link , final Context ctx)
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
                                if(result == "error")
                                {

                                }
                                else {
                                    last_id = Integer.parseInt(result);
                                    //getJsonClient( result );
                                }
                            }
                        }
                    });

    }


    public void getJsonClient( String result )
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

                    json_client.id = Integer.parseInt(oneObject.getString("id"));
                    //json_fam.nbr_plat = Integer.parseInt(oneObject.getString("nbr_plat"));
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                //ClientRepository.list_Client.add(json_client);
            }
        }
        catch (Exception e)
        {

        }
    }

}
