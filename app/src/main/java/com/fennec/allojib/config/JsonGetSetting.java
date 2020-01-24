package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.controller.SplashActivity;
import com.fennec.allojib.controller.ui.coursier.CoursierFragment;
import com.fennec.allojib.entity.Coursier;
import com.fennec.allojib.entity.Setting;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.CoursierRepository;
import com.fennec.allojib.repository.SettingRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetSetting implements IonHandler {


    public JsonGetSetting(String link , final Context ctx)
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
                            Log.d("TAG_JSON", "onClick: SEND URL" + result);
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
        SplashActivity.onSucces();
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
                Setting json_Setting = new Setting();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_Setting.id      = Integer.parseInt(oneObject.getString("id"));
                    json_Setting.app_tel      = oneObject.getString("app_tel");
                    json_Setting.app_coursier   = oneObject.getString("app_coursier");
                    json_Setting.app_des   = oneObject.getString("app_des");

                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                SettingRepository.main_Setting = json_Setting;
            }
        }
        catch (Exception e)
        {

        }
    }

}
