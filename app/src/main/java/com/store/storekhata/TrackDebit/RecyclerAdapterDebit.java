package com.store.storekhata.TrackDebit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.store.storekhata.Login.LoginCallBack;
import com.store.storekhata.R;
import com.store.storekhata.onBoardingStructure.OnBoardingActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.HashSet;
import java.util.List;

public class RecyclerAdapterDebit extends RecyclerView.Adapter<RecyclerAdapterDebit.ViewHolder>{


    Context context;
    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    Activity activity;


    public RecyclerAdapterDebit(Context context, List<Debt_Pojo> debtPojoList, Activity activity) {
        this.context = context;
        this.activity = activity;
        Log.d("loggy"," constructor called and list size is "+debtPojoList.size());

        // make a set in java
        HashSet<String> allQuinqueIds=new HashSet<>();

        for(int i=0;i<debtPojoList.size();i++){
            allQuinqueIds.add(debtPojoList.get(i).getUid());            //adding UID to hashset
            Log.d("hashset",""+allQuinqueIds.size());
        }
        //        now we have all uniwue  ids in set
        ArrayList<Debt_Pojo> correctList= new ArrayList<>();

        for(String key:allQuinqueIds){
            Log.d("values: allQuinqueIds",key);
            Log.d("loggy"," -------------- changing ------key is "+key);
            int any_useul_index=-1;
            int totalAmountForThisUserWithThisId=0;

            for(int i=0;i<debtPojoList.size();i++){
                Log.d("loggy"," current id is "+debtPojoList.get(i).getUid()+" and key is "+key);
                if(debtPojoList.get(i).getUid().equals(key)){
                    Log.d("loggy"," its equal we are adding ");
                    totalAmountForThisUserWithThisId+= Integer.parseInt(debtPojoList.get(i).getTotal());
                    any_useul_index=i;
                }
            }
            Debt_Pojo tempo= debtPojoList.get(any_useul_index);    // used for mapping      tempo obj of Debit_pojo class
            tempo.setTotal(String.valueOf(totalAmountForThisUserWithThisId));
            Log.d("loggy"," now for user with id "+tempo.getUid()+" we have debt of "+totalAmountForThisUserWithThisId);

            correctList.add(tempo);             // After all calculations we have added sum into correctList so in constructor we can diectly assign the values of pojoList to debtPojoList as below
        }
        this.debtPojoList = correctList;
        // now everythimg is weell and saved in correct list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(debtPojoList.get(position).getName());
        holder.Rs.setText("Rs. "+ debtPojoList.get(position).getTotal());
        holder.cir_img.setText(debtPojoList.get(position).getName().substring(0,1).toUpperCase());

        final String uid = debtPojoList.get(position).getUid();
        //Toast.makeText(context, ""+uid, Toast.LENGTH_SHORT).show();

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DebitDetailFragment debitDetailFragment = new DebitDetailFragment();
                FragmentTransaction fragmentTransaction = ((OnBoardingActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.on_boarding_fragment_container,debitDetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Bundle bundle=new Bundle();             // send uid to DebitDetailFragment
                bundle.putString("uid",uid);
                debitDetailFragment.setArguments(bundle);

            }
        });

        //Toast.makeText(context, ""+debtPojoList.get(1).getTotal(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return debtPojoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,Rs,cir_img;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            Rs = itemView.findViewById(R.id.Rs);
            cir_img = itemView.findViewById(R.id.cir_img);
            constraintLayout = itemView.findViewById(R.id.constraintView2);
        }
    }
}
