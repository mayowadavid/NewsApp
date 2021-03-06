package com.sixteenmb.abhishekint.liberty;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 03-06-2017.
 */

public class Top_Data {

    Top.Top_Adapter top_adapter;
    RecyclerView recyclerView;
    List<String> titleList=new ArrayList<>();
    List<String> urlImgList=new ArrayList<>();
    List<String> urlArticle=new ArrayList<>();
    ImageLoader imageLoader=AppController.getmInstance().getmImageLoader();

    String idUrl;

    String URL;

    Top_Data(Top.Top_Adapter top_adapter, RecyclerView recyclerView,String urlId)
    {
        this.idUrl=urlId;
       this.top_adapter=top_adapter;
        this.recyclerView=recyclerView;
    }

    void setItem()
    {
        returnUrl();
         StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                finalData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        AppController.getmInstance().addToRequestQueue(stringRequest,"top_data");
    }

    public  void returnUrl()
    {
        this.URL= "https://newsapi.org/v1/articles?source="+idUrl+"&sortBy=top&apiKey=e1f3c5ef9ff2421080cb1ece283078d2";
    }

    void finalData(String s)
    {
      //  Log.e("entry final","entry final");
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("articles");

            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject jsonObject1=(JSONObject)jsonArray.get(i);
                String title=jsonObject1.getString("title");
                String urlToImage=jsonObject1.getString("urlToImage");
                String urlArticle=jsonObject1.getString("url");

                titleList.add(title);
                urlImgList.add(urlToImage);
                this.urlArticle.add(urlArticle);

            }



            recyclerView.setAdapter(top_adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
