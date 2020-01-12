package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.controller.Order_Plat_Activity;
import com.fennec.allojib.controller.Order_Product_Activity;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.PassOrderPlatRepository;
import com.fennec.allojib.repository.PassOrderProductRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class JsonUrlPassOrderProduct implements IonHandler {

    public boolean result_succes = false;
    public boolean result_error = false;

    public int position;

    public JsonUrlPassOrderProduct(String link , final Context ctx, int position)
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
                            onSucces(result);
                        }else {
                            Log.d("TAG_JSON_ORDER", "Error: Result ");
                        }
                    }
                });
    }

    @Override
    public void onSucces(Object obj)
    {
        ConditionResult( obj.toString() );
        Order_Product_Activity.onSucces();
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
            ParseData(result);
        }
    }

    public void ParseData(String result)
    {
        try
        {
            int id_passOrderProduct = Integer.parseInt(result);

            if(id_passOrderProduct != 406)
            {
                PassOrderProductRepository.list_passOrderProduct.get(position).id = id_passOrderProduct;

                Log.d("TAG_JSON_ORDER", "Wééé: Result " + PassOrderProductRepository.list_passOrderProduct.get(position).id+" "+PassOrderProductRepository.list_passOrderProduct.get(position).nom_collecteur);
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
