package com.store.storekhata.TrackDebit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.store.storekhata.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterDebit extends RecyclerView.Adapter<RecyclerAdapterDebit.ViewHolder>{


    Context context;
    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    Activity activity;

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

            System.out.println(debtPojoList.get(i).getName());
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
