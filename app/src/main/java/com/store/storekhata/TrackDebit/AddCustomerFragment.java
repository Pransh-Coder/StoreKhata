package com.store.storekhata.TrackDebit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;

public class AddCustomerFragment extends Fragment {
    EditText Name,Email,Address,phone;
    Button add_customer;
    String name,email,address,phone_no;

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
        Address = rootview.findViewById(R.id.adress);
        phone = rootview.findViewById(R.id.phone);
        add_customer= rootview.findViewById(R.id.add_customer);

        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                email = Email.getText().toString();
                address=Address.getText().toString();
                phone_no=phone.getText().toString();

                if(Name.getText().length()==0){
                    Name.setError("Name Feild Empty!");
                }
                else if(Email.getText().length()==0){
                    Email.setError("Email Feild Empty!");
                }
                else if(Address.getText().length()==0){
                    Address.setError("Address Feild Empty!");
                }
                else {
                        networkingCalls.addCustomer(name,email,address,phone_no);
                }
            }
        });


        return rootview;
    }

}
