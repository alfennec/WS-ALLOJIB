package com.fennec.allojib.config;

import android.content.Context;
import android.util.Log;

import com.fennec.allojib.entity.OrderProduct;
import com.fennec.allojib.myInterface.IonHandler;
import com.fennec.allojib.repository.OrderProductRepository;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGetOrderProduct implements IonHandler {

    public JsonGetOrderProduct(String link , final Context ctx)
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
                            Log.d("TAG_PRODUCT", "4 - GET FROM SERVER ORDER: " + result);
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
                OrderProduct json_OrderProduct = new OrderProduct();

                try
                {
                    JSONObject oneObject = jArray.getJSONObject(i);

                    json_OrderProduct.id           = Integer.parseInt(oneObject.getString("id"));
                    json_OrderProduct.id_passOrder = Integer.parseInt(oneObject.getString("id_order"));
                    json_OrderProduct.id_product      = Integer.parseInt(oneObject.getString("id_product"));
                    json_OrderProduct.quantity     = Integer.parseInt(oneObject.getString("quatity"));
                }
                catch (JSONException e)
                {
                    //Log.e("tag_json", ""+e);
                }

                OrderProductRepository.list_orderProduct.add(json_OrderProduct);
                //Log.d("TAG_JSON_ORDER", "-----------------------------> TO SEND "+ json_OrderPlat.quantity);
            }
        }
        catch (Exception e)
        {

        }
    }
}

