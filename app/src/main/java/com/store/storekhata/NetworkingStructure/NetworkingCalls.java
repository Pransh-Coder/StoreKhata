package com.store.storekhata.NetworkingStructure;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.store.storekhata.Login.LoginCallBack;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.TrackDebit.Debt_Pojo;
import com.store.storekhata.TrackDebit.RecyclerAdapterDebit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.store.storekhata.NetworkingStructure.URLS.BASE_URL;

public class NetworkingCalls {

    private Context context;
    private RequestQueue requestQueue;
    private Activity activity;
    private SharePrefs sharePrefs;

    List<Debt_Pojo> debtPojoList = new ArrayList<>();

    String id;

    LoginCallBack loginCallBack;

    public NetworkingCalls(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        requestQueue = Volley.newRequestQueue(this.context);
        sharePrefs = new SharePrefs(context);
        loginCallBack = (LoginCallBack) activity;
    }

    private void addToQueue(StringRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(30),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public void adminLogin(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "AdminLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("Response", response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        loginCallBack.AuthenticateUser();

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        Log.e("array" , jsonArray.toString());

                         // to make the value true from false and in splashscreen we will check whether value is true or false if true directly into the app else in LoginScreen
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject temp = (JSONObject) jsonArray.get(i);
                            Log.e("ob",temp.toString());
                            String AID = temp.getString("AID");
                            String name =  temp.getString("Name");
                            String password = temp.getString("Password");

                            Log.e("repo ", " name :" + temp.getString("Name") + " and store " );

                            sharePrefs.setLoggedIn(true);
                            sharePrefs.putAID(AID);
                            sharePrefs.putName(name);

                            id = sharePrefs.getAID();
                            Log.e("id", id);


                        }
                        //loginCallBack.AuthenticateUser();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Login", error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        addToQueue(stringRequest);
    }

    public List<Debt_Pojo> showPersonsDebit(final RecyclerView recyclerView) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "GetAdminClients.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("showPersonsDebit", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Debt");


                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                            final Debt_Pojo debt_pojo = new Debt_Pojo();

                            String total = jsonObject1.getString("Total");
                            String debit_id=jsonObject1.getString("DebtId");
                            debt_pojo.setUid(jsonObject1.getString("UID"));
                            debt_pojo.setName(jsonObject1.getString("Name"));
                            debt_pojo.setTotal(jsonObject1.getString("Total"));
                            debt_pojo.setDebtId(jsonObject1.getString("DebtId"));

                            debtPojoList.add(debt_pojo);
                            Log.d("repo_debit ", total+ "Debit_id:"+ debit_id) ;

                            Bundle bundle = new Bundle();
                            bundle.putString("debit_id", debit_id);


                        }
//                        recyclerAdapterDebit.notifyDataSetChanged();
                        RecyclerAdapterDebit recyclerAdapterDebit = new RecyclerAdapterDebit(context,/*giveMeFakeData.giveMeFakeData()*/debtPojoList,activity);        //To bring recyclerAdapter in scope to recyclerView.setAdapter(recyclerAdapterDebit);
                        recyclerView.setAdapter(recyclerAdapterDebit);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volleyError",error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("aid", sharePrefs.getAID());
                return params;
            }
        };
        addToQueue(stringRequest);
        return debtPojoList;
    }
    public void addCustomer(final String Name, final String email, final String password, final String phoneNo, final String storeName){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "UserSignUp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("addCustomer response",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",Name);
                params.put("email",email);
                params.put("phone",phoneNo);
                params.put("password",password);
                params.put("aid",sharePrefs.getAID());
                params.put("store",storeName);
                return params;

            }
        };
        addToQueue(stringRequest);
    }
}
