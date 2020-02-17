package com.store.storekhata.TrackDebit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.store.storekhata.Login.LoginCallBack;
import com.store.storekhata.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerAdapterDebit extends RecyclerView.Adapter<RecyclerAdapterDebit.ViewHolder>{


    Context context;
    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    Activity activity;

    ArrayList<Debt_Pojo> doubleList;

    public RecyclerAdapterDebit(Context context, List<Debt_Pojo> debtPojoList, Activity activity) {
        this.context = context;
        this.debtPojoList = debtPojoList;
        this.activity = activity;
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
        holder.cir_img.setText(debtPojoList.get(position).getName().substring(0,1));

        //Toast.makeText(context, ""+debtPojoList.get(1).getTotal(), Toast.LENGTH_SHORT).show();

        for(int i=0;i<debtPojoList.size();i++){

            //System.out.println(debtPojoList.get(i).getName());

            doubleList = new ArrayList<>();
            if (!doubleList.contains(debtPojoList.get(i).name)) {
                for (int j = 0 ; j < doubleList.size() ; j++)
                {
                    if (doubleList.get(j).getName().equals(debtPojoList.get(i).getName())){
                        Debt_Pojo debtPojo = new Debt_Pojo();
                        debtPojo.setName(debtPojoList.get(i).getName());
                        debtPojo.setDebtId(debtPojoList.get(i).getDebtId());
                        debtPojo.setItemName(debtPojoList.get(i).getItemName());
                        debtPojo.setPriceOfOne(debtPojoList.get(i).getPriceOfOne());
                        debtPojo.setQuantity(debtPojoList.get(i).getQuantity());
                        debtPojo.setUid(debtPojoList.get(i).getUid());
                        debtPojo.setTotal(debtPojoList.get(i).getTotal()+doubleList.get(i).getTotal());
                        doubleList.set(j,debtPojo);
                    }
                }

            }
            else{
                doubleList.add(debtPojoList.get(i));
            }

        }
        for (int j =0 ;j<doubleList.size();j++){
            Log.e("abc","abc:"+doubleList.get(j).getTotal()+":"+doubleList.get(j).getName());
        }

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
