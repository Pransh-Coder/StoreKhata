package com.store.storekhata.NetworkingStructure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import com.store.storekhata.Login.SignupCallBack;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;
import com.store.storekhata.TrackDebit.Debt_Pojo;
import com.store.storekhata.TrackDebit.RecyclerAdapterDebit;
import com.store.storekhata.TrackDebit.RecyclerAdapterItemHistory;
import com.store.storekhata.TrackDebit.RecyclerAdapterShowEachItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.store.storekhata.NetworkingStructure.URLS.BASE_URL;

public class NetworkingCalls {

    private Context context;
    private RequestQueue requestQueue;
    private Activity activity;                      //??
    private SharePrefs sharePrefs;
    private UserSharedPrefs userSharedPrefs;

    ProgressDialog dialog;

    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    List<Debt_Pojo> debtPojoList2 = new ArrayList<>();
    List<Debt_Pojo> debt_pojoList = new ArrayList<>();

    String id;
    String date;

    LoginCallBack loginCallBack;
    SignupCallBack signupCallBack;


    public NetworkingCalls(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        requestQueue = Volley.newRequestQueue(this.context);
        sharePrefs = new SharePrefs(context);
        userSharedPrefs= new UserSharedPrefs(context);
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);
        loginCallBack = (LoginCallBack) activity;
        signupCallBack = (SignupCallBack) activity;     //??? why we do this

    }

    private void addToQueue(StringRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(30),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public void adminSignup(final String email, final String password, final String name, final String storename) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "AdminSignup.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    Log.e("responseSignup", response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        signupCallBack.AuthenticateSignup();

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

                      /*     sharePrefs.setLoggedIn(true);
                            sharePrefs.putAID(AID);
                            sharePrefs.putName(name);

                            id = sharePrefs.getAID();
                            Log.e("id", id);
*/

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
                params.put("name", name);
                params.put("store", storename);
                return params;
            }
        };
        addToQueue(stringRequest);
    }

    public void adminLogin(final String email, final String password) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "AdminLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    Log.e("adminResponse", response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        //loginCallBack.AuthenticateAdmin();

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
                            sharePrefs.putAID(AID);;
                            //sharePrefs.putName(name);

                            Log.e("putName",name);
                            //We cannot send UID from here beco when admin signups using AdminLogin.php API in response no UID is coming
                            id = sharePrefs.getAID();
                            Log.e("id", id);


                        }
                        //loginCallBack.AuthenticateUser();
                        loginCallBack.AuthenticateAdmin();
                    }
                    else {
                        Toast.makeText(context, "Wrong Email or Password!!", Toast.LENGTH_SHORT).show();
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
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "GetAdminClients.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("showPersonsDebit", response);
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Debt");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                            final Debt_Pojo debt_pojo = new Debt_Pojo();

                            String total = jsonObject1.getString("Total");
                            String debit_id=jsonObject1.getString("DebtId");
                            String Uid = jsonObject1.getString("UID");

                            debt_pojo.setQuantity(jsonObject1.getString("Quantity"));
                            debt_pojo.setPriceOfOne(jsonObject1.getString("PriceOfOne"));
                            debt_pojo.setItemName(jsonObject1.getString("ItemName"));

                            debt_pojo.setUid(jsonObject1.getString("UID"));
                            debt_pojo.setName(jsonObject1.getString("Name"));
                            debt_pojo.setTotal(jsonObject1.getString("Total"));
                            debt_pojo.setDebtId(jsonObject1.getString("DebtId"));

                            debtPojoList.add(debt_pojo);

                            Log.d("repo_debit ", "total: "+total+ " Debit_id:"+ debit_id + " uid: "+Uid) ;

                            if(sharePrefs.isLoggedIn()){

                                sharePrefs.putUID(Uid);
                                //sharePrefs.putName(jsonObject1.getString("Name"));

                            }
                            else {
                                userSharedPrefs.putUID_forUserLogin(Uid);

                            }

                        }
//                        recyclerAdapterDebit.notifyDataSetChanged();
                        RecyclerAdapterDebit recyclerAdapterDebit = new RecyclerAdapterDebit(context,/*giveMeFakeData.giveMeFakeData()*/debtPojoList,activity);        //To bring recyclerAdapter in scope to recyclerView.setAdapter(recyclerAdapterDebit);
                        recyclerView.setAdapter(recyclerAdapterDebit);

                        /*RecyclerAdapterShowEachItem recyclerAdapterShowEachItem = new RecyclerAdapterShowEachItem(context,debtPojoList,activity);
                        recyclerView.setAdapter(recyclerAdapterShowEachItem);*/
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
                Log.d("before sending AID,",userSharedPrefs.getAID_forUserLogin());
                if(sharePrefs.isLoggedIn()){
                    params.put("aid", sharePrefs.getAID());
                }
                else {
                    params.put("aid", userSharedPrefs.getAID_forUserLogin());
                }

                return params;
            }
        };
        addToQueue(stringRequest);
        return debtPojoList;
    }
//Open Debit Details of Paticular Person
    public List<Debt_Pojo> showDebitDetails(final RecyclerView recyclerView, final String id) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "GetAdminClients.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("showDebitDetails", response);
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Debt");


                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                            final Debt_Pojo debt_pojo = new Debt_Pojo();

                            String uid = jsonObject1.getString("UID");
                            Log.e("show***uid***",uid + " id:"+id);

                            String name = jsonObject1.getString("Name");

                            if(id.equals(uid)){                                                     // This id is coming from DebitDetailFragment and this id is basically (uid)
                                debt_pojo.setQuantity(jsonObject1.getString("Quantity"));
                                debt_pojo.setPriceOfOne(jsonObject1.getString("PriceOfOne"));
                                debt_pojo.setItemName(jsonObject1.getString("ItemName"));
                                debt_pojo.setTotal(jsonObject1.getString("Total"));
                                debt_pojo.setDebtId(jsonObject1.getString("DebtId"));
                                debt_pojo.setDate(jsonObject1.getString("date"));

                                debtPojoList2.add(debt_pojo);

                                if(sharePrefs.isLoggedIn()){

                                    sharePrefs.putUID(uid);
                                    sharePrefs.putName(name);

                                }
                            }
                            /*debt_pojo.setQuantity(jsonObject1.getString("Quantity"));
                            debt_pojo.setPriceOfOne(jsonObject1.getString("PriceOfOne"));
                            debt_pojo.setItemName(jsonObject1.getString("ItemName"));
                            debt_pojo.setTotal(jsonObject1.getString("Total"));
                            debt_pojo.setDebtId(jsonObject1.getString("DebtId"));

                            debtPojoList2.add(debt_pojo);*/

                            //sharePrefs.putUID(uid);

                        }
//                        recyclerAdapterDebit.notifyDataSetChanged();
                        RecyclerAdapterShowEachItem recyclerAdapterShowEachItem = new RecyclerAdapterShowEachItem(context,debtPojoList2,activity);
                        recyclerView.setAdapter(recyclerAdapterShowEachItem);
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
                //params.put("aid", sharePrefs.getAID());
                if(sharePrefs.isLoggedIn()){
                    params.put("aid", sharePrefs.getAID());
                }
                else {
                    params.put("aid", userSharedPrefs.getAID_forUserLogin());
                }
                return params;
            }
        };
        addToQueue(stringRequest);
        return debtPojoList2;
    }

    public void addCustomer(final String Name, final String email, final String password, final String phoneNo){
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "UserSignUp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
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
                /*params.put("store",storeName);*/
                return params;

            }
        };
        addToQueue(stringRequest);
    }

    public void userLogin(final String email, final String password) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "UserLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Log.e("userResponse", response);
                try {
                    Log.e("Response", response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {

                        String AID, UID = "";
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        Log.e("array" , jsonArray.toString());

                        // to make the value true from false and in splashscreen we will check whether value is true or false if true directly into the app else in LoginScreen
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject temp = (JSONObject) jsonArray.get(i);
                            Log.e("ob",temp.toString());
                            AID = temp.getString("AID");
                            UID =  temp.getString("UID");
                            String name = temp.getString("Name");

                            Log.e("userLogin ",AID+ " "+ UID );

                            userSharedPrefs.setLoggedInUser(true);
                            userSharedPrefs.putAID_forUserLogin(AID);
                            userSharedPrefs.putUID_forUserLogin(UID);       // we have directly saved the uid in user shared prefrence and directly passed to DebitDetailFragment in else part of it
                            userSharedPrefs.putName_forUserLogin(name);

                            Log.e("putNameUser",name);

                        }
                         //loginCallBack.Authenticateuser(UID);            // sending UID to logincallback interface of function Authenticateuser(UID) which is called

                         loginCallBack.AuthUser();          //Used for making transition to DebitDetailFragment and not passing UID
                    }
                    else {
                        Toast.makeText(context, "Wrong Email/Password!", Toast.LENGTH_SHORT).show();
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

    public void deleteItem(final String debitId) {
        dialog.show();
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.e("deleteItem_func_date",date);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "removeDebt.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Log.d("deleteItem",response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("debtId",debitId);
                map.put("date",date);
                return map;
            }
        };
        addToQueue(stringRequest);
    }

    public List<Debt_Pojo> ItemsHistory(final String uid, final String aid, final RecyclerView recyclerView) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "GetAllUserDebt.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Log.d("alldebts",response);
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Log.e("date",date);

                    JSONObject jsonObject = new JSONObject(response);               //convert String response into JsonObject
                    if(jsonObject.getString("status").equals("true")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Debt");          // getting the jsonArray

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);

                            Debt_Pojo debt_pojo = new Debt_Pojo();

                            debt_pojo.setItemName(jsonObject1.getString("ItemName"));
                            debt_pojo.setQuantity(jsonObject1.getString("Quantity"));
                            debt_pojo.setPriceOfOne(jsonObject1.getString("PriceOfOne"));
                            debt_pojo.setTotal(jsonObject1.getString("Total"));
                            debt_pojo.setDate(jsonObject1.getString("ClearDate"));

                            debt_pojoList.add(debt_pojo);

                        }
                        RecyclerAdapterItemHistory recyclerAdapterItemHistory = new RecyclerAdapterItemHistory(context,debt_pojoList,activity);
                        recyclerView.setAdapter(recyclerAdapterItemHistory);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("uid",uid);
                map.put("aid",aid);
                return map;
            }
        };
        addToQueue(stringRequest);
      return debtPojoList;
    }

    public void addItem(final String itemNam, final String qty, final String pr, final String personNam, final String uid, final String aid) {
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "DebtPost.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Log.d("addItem response",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("item",itemNam);
                params.put("quantity",qty);
                params.put("name",personNam);
                params.put("price",pr);
                params.put("aid",aid);
                params.put("uid",uid);
                return params;

            }
        };
        addToQueue(stringRequest);
    }
}
