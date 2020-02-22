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


public class DebitDetailFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    NetworkingCalls networkingCalls;

    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_debit_detail, container, false);
        networkingCalls = new NetworkingCalls(getContext(),getActivity());

        if (getArguments() != null) {                                               //getting value from RecyclerAdapterDebit for Admin  and for userLogin sending uid directly from loginCallback Interface
            uid = getArguments().getString("uid","");
            Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
        }
        recyclerView = rootview.findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        networkingCalls.showDebitDetails(recyclerView,uid);
        return rootview;
    }

}
