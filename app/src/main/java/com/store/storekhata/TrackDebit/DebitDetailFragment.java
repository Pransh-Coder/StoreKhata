package com.store.storekhata.TrackDebit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.store.storekhata.R;


public class DebitDetailFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_debit_detail, container, false);

        if (getArguments() != null) {
            String uid = getArguments().getString("uid","");
            Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
            // selectedId=selectedId_from_mainActivity;
        }
        return rootview;
    }

}
