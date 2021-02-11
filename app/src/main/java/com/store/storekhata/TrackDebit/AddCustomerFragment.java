package com.store.storekhata.TrackDebit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;

public class AddCustomerFragment extends Fragment {
    EditText Name,Email,Password,phone,StoreName;
    Button add_customer;
    String name,email,password,phone_no,store_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_add_customer, container, false);

        final NetworkingCalls networkingCalls = new NetworkingCalls(getContext(),getActivity());
        Name = rootview.findViewById(R.id.name);
        Email = rootview.findViewById(R.id.email);
        Password = rootview.findViewById(R.id.password);
        phone = rootview.findViewById(R.id.phone);
        add_customer= rootview.findViewById(R.id.add_customer);
       // StoreName = rootview.findViewById(R.id.store);

        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkInput()){

                    networkingCalls.addCustomer(name,email,password,phone_no);
                    Name.getText().clear();
                    Email.getText().clear();
                    Password.getText().clear();
                    phone.getText().clear();
                    showCustomerDialog();
                }
            }
        });
        return rootview;
    }

    public boolean checkInput(){

        if(Name.getText().toString().isEmpty()){
            Name.setError("Name Feild Empty!");
            return false;
        }
        else if(Email.getText().toString().isEmpty()){
            Email.setError("Email Feild Empty!");
            return false;
        }
        else if(Password.getText().toString().isEmpty()){
            Password.setError("Password Feild Empty!");
            return false;
        }
        else if (phone.getText().toString().isEmpty()){
            phone.setError("Phone Feild Empty!");
            return false;
        }
        else if (phone.getText().length()!=10){
            phone.setError("Please enter a valid phone number!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches())
        {
            Email.setError("Please enter a valid email");
            return false;
        }
        else if(!Patterns.PHONE.matcher(phone.getText().toString()).matches())
        {
            phone.setError("Please enter a valid phone number!");
            return false;
        }
        return true;
    }

    private void showCustomerDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.added_customer_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        ImageView cross = dialog.findViewById(R.id.cross);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
