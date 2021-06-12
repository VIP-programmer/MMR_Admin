package com.example.mmradmin;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Model {
    private Context context;
    private RequestQueue queue;
    private StringRequest request;



    public interface SignUpCallBack{
        void onSuccess(String message);
        void onErr(String message);
    }
    public interface LoadHomeInfoCallBack{
        void onSuccess(Vector<Medcin> vector);
        void onErr(String message);
    }
    public Model(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }
    public void getUsers(LoadHomeInfoCallBack callBack){

        String url = Config.URL+"/Model/admin/get_users.php";

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TAG", "onResponse: "+response);
                    Vector<Medcin> medcins = new Vector<>();
                    //Vector<Notes.Note> notes = new Vector<>();
                    JSONArray medsArray = new JSONArray(response);
                    for (int i = 0; i < medsArray.length(); i++) {
                        medcins.add(new Medcin(medsArray.getJSONObject(i).getString("cin"),
                                medsArray.getJSONObject(i).getString("serie"),
                                medsArray.getJSONObject(i).getString("nom"),
                                medsArray.getJSONObject(i).getString("prenom")
                        ));
                    }
                    callBack.onSuccess(medcins);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Log.d("TAG", "onErrorResponse: " + error.getMessage());
                    callBack.onErr("Impoussible de se connecter");
                }else if (error instanceof VolleyError)
                    callBack.onErr("Une erreur s'est produite");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map= new HashMap<String, String>();
                return map;
            }
        };
        request.setTag("TAG");
        queue.add(request);
    }
    public void giveAcces(String cin, SignUpCallBack callBack){

        String url = Config.URL+"/Model/admin/give_acces.php";

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TAG", "onResponse: "+response);
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");
                    if (!error){
                        callBack.onSuccess("Autorisation a été donnée");
                    }else{
                        Log.i("tagconvertstr", "["+response+"]");
                        String messages = json.getString("messages");
                        callBack.onErr(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Log.d("TAG", "onErrorResponse: " + error.getMessage());
                    callBack.onErr("Impoussible de se connecter");
                }else if (error instanceof VolleyError)
                    callBack.onErr("Une erreur s'est produite");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map= new HashMap<String, String>();
                map.put("cin",cin);
                return map;
            }
        };
        request.setTag("TAG");
        queue.add(request);
    }
    public void addMedicament(Map<String,String> infos, SignUpCallBack callBack){

        String url = Config.URL+"/Model/admin/add_medicament.php";

        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("TAG", "onResponse: "+response);
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");
                    if (!error){
                        callBack.onSuccess("Medicament est ajouté !");
                    }else{
                        Log.i("tagconvertstr", "["+response+"]");
                        String messages = json.getString("messages");
                        callBack.onErr(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Log.d("TAG", "onErrorResponse: " + error.getMessage());
                    callBack.onErr("Impoussible de se connecter");
                }else if (error instanceof VolleyError)
                    callBack.onErr("Une erreur s'est produite");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return infos;
            }
        };
        request.setTag("TAG");
        queue.add(request);
    }
}
