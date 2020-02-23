package com.store.storekhata.Login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;

public class SignUpFragment extends Fragment {

    EditText Name,Email,Password,StoreName,mobileNo;
    String name,email,password,shopName,mobilenum;
    Button Signup;
    TextView textButton;
    NetworkingCalls networkingCalls;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_sign_up, container, false);

        networkingCalls =new NetworkingCalls(getContext(),getActivity());

        Name = rootview.findViewById(R.id.nam);
        Email = rootview.findViewById(R.id.ema);
        Password = rootview.findViewById(R.id.pass);
        StoreName = rootview.findViewById(R.id.storeNam);
        textButton=rootview.findViewById(R.id.textButton);
        Signup = rootview.findViewById(R.id.btnSignUp);
        //mobileNo = rootview.findViewById(R.id.mobileNo);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email =Email.getText().toString();
                password = Password.getText().toString();
                shopName = StoreName.getText().toString();
                name = Name.getText().toString();
                //mobilenum=mobileNo.getText().toString();

                if(Email.getText().toString().length()==0)
                {
                    Email.setError("Enter Email!");
                }
                else if(Password.getText().toString().length()==0)
                {
                    Password.setError("Password feild is empty!");

                }
                else if(Name.getText().toString().length()==0)
                {
                    Name.setError("Name feild is empty!");
                }
                else if(StoreName.getText().toString().length()==0){
                    StoreName.setError("Store Name feild is empty!");
                }
                /*else if(mobileNo.getText().length()==0){
                    mobileNo.setError("Mobile Number feild empty!");
                }*/
                else
                {
                    networkingCalls.adminSignup(email,password,name,shopName);
                }
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginFragment loginFragment = new LoginFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.on_boarding_fragment_container,loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return  rootview;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
