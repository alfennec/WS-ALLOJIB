package com.fennec.allojib.config;

import android.content.Context;

import com.fennec.allojib.myInterface.IonHandler;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class JsonUrlOrderProduct implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;

    public JsonUrlOrderProduct(String link , final Context ctx)
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
                            onSucces(result);
                        }
                    }
                });

    }

    @Override
    public void onSucces(Object obj)
    {
        //ConditionResult(obj.toString());

    }

    @Override
    public void onFailed(Object obj)
    {

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
        }
    }

}
