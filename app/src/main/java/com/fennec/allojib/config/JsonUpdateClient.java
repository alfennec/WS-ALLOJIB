package com.fennec.allojib.config;

import android.content.Context;

import com.fennec.allojib.controller.MainActivity;
import com.fennec.allojib.controller.ui.profile.ProfileFragment;
import com.fennec.allojib.entity.Client;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.ClientRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUpdateClient implements IonHandler {


    public JsonUpdateClient(String link , final Context ctx)
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
                            onSucces(result);
                        }else
                        {
                            onFailed(-1);
                        }
                    }
                });
    }

    @Override
    public void onSucces(Object obj)
    {
        String result = obj.toString().replaceAll("\\s","");

        //Log.d("TAG_JSON_LOGIN", "onClick: SEND"+result+"rt");
        try
        {
            if(Integer.parseInt(result) == 406 )
            {
                ProfileFragment.OnFailedLogin();
            }
        }catch (Exception e)
        {
            ProfileFragment.OnSuccesLogin();
        }



    }

    @Override
    public void onFailed(Object obj)
    {

    }

}
