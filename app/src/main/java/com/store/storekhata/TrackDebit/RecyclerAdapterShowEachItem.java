package com.store.storekhata.TrackDebit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterShowEachItem extends RecyclerView.Adapter<RecyclerAdapterShowEachItem.ViewHolder> {

    Context context;
    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    Activity activity;
    NetworkingCalls networkingCalls;
    String debitId;

    public RecyclerAdapterShowEachItem(Context context, List<Debt_Pojo> debtPojoList, Activity activity) {
        this.context = context;
        this.debtPojoList = debtPojoList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        holder.ItemName.append(" "+debtPojoList.get(position).getItemName());
        holder.Qty.append(" "+debtPojoList.get(position).getQuantity());
        holder.PriceOfOne.append(" Rs."+debtPojoList.get(position).getPriceOfOne());
        holder.Total.append(" Rs."+debtPojoList.get(position).getTotal());

        networkingCalls = new NetworkingCalls(context,activity);
        debitId = debtPojoList.get(position).getDebtId();

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                //send debitI

                networkingCalls.deleteItem(debitId);
                holder.constraintLayout.setVisibility(View.GONE);

                Toast.makeText(context, "Deleted ! Item BUT NO API Attached", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return debtPojoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView deleteItem;
        TextView ItemName,PriceOfOne,Total,Qty;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ItemName = itemView.findViewById(R.id.itemName);
            PriceOfOne = itemView.findViewById(R.id.priceOfOne);
            Total = itemView.findViewById(R.id.totalPrice);
            Qty = itemView.findViewById(R.id.qty);
            deleteItem = itemView.findViewById(R.id.delete);
            constraintLayout = itemView.findViewById(R.id.constraintView2);
        }
    }
}
