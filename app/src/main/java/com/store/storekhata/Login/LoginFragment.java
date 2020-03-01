package com.store.storekhata.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.TrackDebit.TrackYourDebtFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    RequestQueue requestQueue;
    int flag=0;
    SharePrefs sharePrefs;
    NetworkingCalls networkingCalls;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    EditText name,shop_name,email,password;
    LinearLayout userLinear,adminLinear;
    Button login;
    TextView textlogin,txt_who;
    String Name,Email,Password,ShopName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_login, container, false);

        requestQueue= Volley.newRequestQueue(getContext());

        sharePrefs = new SharePrefs(getContext());
        networkingCalls = new NetworkingCalls(getContext(),getActivity());

        email = rootview.findViewById(R.id.edt_email);
        password=rootview.findViewById(R.id.edt_password);
        login = rootview.findViewById(R.id.btnAuth);
        textlogin = rootview.findViewById(R.id.textlogin);
        userLinear= rootview.findViewById(R.id.userLinear);
        adminLinear = rootview.findViewById(R.id.adminLinear);
        txt_who= rootview.findViewById(R.id.txt_who);

        userLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_who.setText("User Login");
                flag=1;
            }
        });

        adminLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_who.setText("Admin Login");
                flag=2;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(), "clicked!", Toast.LENGTH_SHORT).show();

                Email =email.getText().toString();
                Password = password.getText().toString();

                if(email.getText().toString().length()==0)
                {
                    email.setError("Enter Email!");
                }
                else if(password.getText().toString().length()==0)
                {
                    password.setError("Password feild is empty!");

                }
                else
                {
                    if(flag==2){

                        networkingCalls.adminLogin(Email,Password);
                    }
                    else if(flag==1){
                        networkingCalls.userLogin(Email,Password);
                    }
                    else {
                        networkingCalls.adminLogin(Email,Password);
                    }
                    /*Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);*/
                }

            }
        });

        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.on_boarding_fragment_container,signUpFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootview;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
