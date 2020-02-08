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
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;

public class TrackYourDebtFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapterDebit adapter;
    RecyclerView.LayoutManager layoutManager;

    Button addbtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_track_your_debt, container, false);
        NetworkingCalls networkingCalls = new NetworkingCalls(getContext(),getActivity());
        addbtn = rootView.findViewById(R.id.addCust);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        networkingCalls.showPersonsDebit(recyclerView);
        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
