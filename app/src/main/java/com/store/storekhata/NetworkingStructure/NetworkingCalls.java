package com.store.storekhata.NetworkingStructure;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.store.storekhata.SharePrefrence.SharePrefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.store.storekhata.NetworkingStructure.URLS.BASE_URL;

public class NetworkingCalls {

    private Context context;
    private RequestQueue requestQueue;
    private Activity activity;
    private SharePrefs sharePrefs;

    public NetworkingCalls(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        requestQueue = Volley.newRequestQueue(this.context);
        sharePrefs = new SharePrefs(context);
    }
    private void addToQueue(StringRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(30),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public void adminLogin(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL+"AdminLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("Response",response);
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("true")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject temp = (JSONObject) jsonArray.get(i);
                            String AID = temp.getString("AID");
                            Log.d("repo "," name :"+temp.getString("Name")+" and store "+temp.getString("AID"));
                            sharePrefs.putAID(AID);

                            String id = sharePrefs.getAID();
                            Log.e("id",id);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Login",error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password",password);
                return params;
            }
        };
        addToQueue(stringRequest);
    }
}
