package com.store.storekhata.TrackDebit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;

public class ItemsHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    UserSharedPrefs userSharedPrefs;
    NetworkingCalls networkingCalls;
    SharePrefs sharePrefs;

    String UID;
    String AID;

    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_items_history, container, false);

        recyclerView = rootview.findViewById(R.id.recycler_view);
        layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        userSharedPrefs = new UserSharedPrefs(getContext());
        networkingCalls = new NetworkingCalls(getContext(),getActivity());
        sharePrefs = new SharePrefs(getContext());
       /* if (getArguments() != null) {    //Not working becoz when AdminLogins no uid is coming from server
            uid = getArguments().getString("uid_fromDebitDetailFragment","");
            Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
        }*/


        if (userSharedPrefs.isLoggedInUser()){
            UID = userSharedPrefs.getUID_forUserLogin();
            AID = userSharedPrefs.getAID_forUserLogin();
        }

        else {
            UID= sharePrefs.getUID();
            AID=sharePrefs.getAID();
        }

        //Toast.makeText(getContext(), "uid: "+UID +" & aid: " + AID, Toast.LENGTH_SHORT).show();
        networkingCalls.ItemsHistory(UID,AID,recyclerView);


        return rootview;
    }

}
