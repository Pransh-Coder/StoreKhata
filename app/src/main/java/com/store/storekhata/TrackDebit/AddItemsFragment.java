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
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;

public class AddItemsFragment extends Fragment {

    EditText itemName,quantity,price,personName;
    String itemNam,qty,pr,personNam;
    Button add_item;
    UserSharedPrefs userSharedPrefs;
    NetworkingCalls networkingCalls;
    SharePrefs sharePrefs;

    String UID,AID,Name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_add_items, container, false);

        userSharedPrefs= new UserSharedPrefs(getContext());
        networkingCalls= new NetworkingCalls(getContext(),getActivity());
        sharePrefs = new SharePrefs(getContext());

        itemName = rootview.findViewById(R.id.Item_name);
        quantity = rootview.findViewById(R.id.item_qty);
        price = rootview.findViewById(R.id.item_priceOfOne);
        //personName = rootview.findViewById(R.id.person_name);
        add_item=rootview.findViewById(R.id.add_item);

        if (sharePrefs.isLoggedIn()){                           // for admin
            UID = sharePrefs.getUID();
            AID = sharePrefs.getAID();
            Name = sharePrefs.getName();
        }
        else {
            UID = userSharedPrefs.getUID_forUserLogin();
            AID = userSharedPrefs.getAID_forUserLogin();
            Name = userSharedPrefs.getName_forUserLogin();
        }

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemNam = itemName.getText().toString();
                qty = quantity.getText().toString();
                pr=price.getText().toString();
                //personNam=personName.getText().toString();

                if(itemName.getText().length()==0){
                    itemName.setError("ItemName Feild Empty!");
                }
                else if(quantity.getText().length()==0){
                    quantity.setError("Quantity Feild Empty!");
                }
                else if(price.getText().length()==0){
                    price.setError("Price Feild Empty!");
                }
                /*else if(personName.getText().length()==0){
                    personName.setError("Person Name Feild Empty!");
                }*/
                else {
                    networkingCalls.addItem(itemNam,qty,pr,Name,UID,AID);
                    itemName.getText().clear();
                    quantity.getText().clear();
                    price.getText().clear();
                    //personName.getText().clear();
                }
            }
        });

        return rootview;
    }

}
