package com.store.storekhata.TrackDebit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;


public class DebitDetailFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    //FloatingActionButton Show_items_history,addItems;
    NetworkingCalls networkingCalls;
    UserSharedPrefs userSharedPrefs;

    String uid;

    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton Show_items_history,addItems;

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
        userSharedPrefs = new UserSharedPrefs(getContext());

       /* Show_items_history = rootview.findViewById(R.id.itemsHistory);
        addItems =rootview.findViewById(R.id.addItems);*/


        materialDesignFAM = (FloatingActionMenu) rootview.findViewById(R.id.material_design_android_floating_action_menu);
        addItems =  rootview.findViewById(R.id.addItems);
        Show_items_history =  rootview.findViewById(R.id.itemsHistory);
        //floatingActionButton3 = (FloatingActionButton) rootview.findViewById(R.id.material_design_floating_action_menu_item3);


        //For UserLogin - uid is sent from networking class itself using this interface loginCallBack.Authenticateuser(UID);

        if (getArguments() != null) {    //for AdminLogin - getting value from RecyclerAdapterDebit
            uid = getArguments().getString("uid","");
            //Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
        }
        //else is used so that sometimes after userLogin we send uid in parametrs of Authenticateuser(UID)

        //any how we have to supply UID here  ****[it is used when  using usetLogin]****            by this[ userSharedPrefs.putUID_forUserLogin(UID);] code we send uid from netorking class to DebitDetailFrag
        else {
            uid=userSharedPrefs.getUID_forUserLogin();
            //Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
        }
        recyclerView = rootview.findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        networkingCalls.showDebitDetails(recyclerView,uid);

        Show_items_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemsHistoryFragment itemsHistoryFragment = new ItemsHistoryFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.on_boarding_fragment_container,itemsHistoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                /*Bundle bundle = new Bundle();
                bundle.putString("uid_fromDebitDetailFragment",uid);
                itemsHistoryFragment.setArguments(bundle);*/
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemsFragment addItemsFragment = new AddItemsFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.on_boarding_fragment_container,addItemsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
/*

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });*/
        return rootview;
    }

}
