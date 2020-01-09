package com.fennec.allojib.config;

import android.content.Context;

import com.fennec.allojib.controller.ui.coursier.CoursierFragment;
import com.fennec.allojib.controller.ui.profile.ProfileFragment;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.entity.Coursier;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.ClientRepository;
import com.fennec.allojib.repository.CoursierRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetCoursier implements IonHandler {


    public JsonGetCoursier(String link , final Context ctx)
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
                            //Log.d("TAG_JSON", "onClick: SEND URL" + result +"LPR");
                            Main_Function(result);
                        }else
                        {
                            onFailed(-1);
                        }
                    }
                });
    }

    public void Main_Function(String result)
    {
        String result_failed = result.replaceAll("\\s","");
        String result_succes = result;

        //Log.d("TAG_JSON_LOGIN", "onClick: SEND"+result+"rt");
        try
        {
            if(Integer.parseInt(result_failed) == 406 )
            {
                onFailed(result);
            }
        }catch (Exception e)
        {
            ParseData(result_succes);
            onSucces(result_succes);

        }
    }

    @Override
    public void onSucces(Object obj)
    {
        CoursierFragment.onSucces();
    }

    @Override
    public void onFailed(Object obj)
    {

    }

    public void ParseData(String result)
    {
        try
        {
            //JSONObject jObject = new JSONObject(result);
            JSONArray jArray = new JSONArray(result);

            for (int i=0; i < jArray.length(); i++)
            {
                Coursier json_coursier = new Coursier();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_coursier.id      = Integer.parseInt(oneObject.getString("id"));
                    json_coursier.id_client      = Integer.parseInt(oneObject.getString("id"));
                    json_coursier.adr_col   = oneObject.getString("adr_col");
                    json_coursier.adr_liv   = oneObject.getString("adr_liv");
                    json_coursier.detail   = oneObject.getString("detail");
                    json_coursier.date_col   = oneObject.getString("date_col");
                    json_coursier.heure_col   = oneObject.getString("heure_col");
                    json_coursier.tel   = oneObject.getString("tel");
                    json_coursier.situation   = Integer.parseInt(oneObject.getString("situation"));

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                CoursierRepository.list_coursier.add(json_coursier);
            }
        }
        catch (Exception e)
        {

        }
    }

}
