package com.store.storekhata.TrackDebit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.store.storekhata.NetworkingStructure.NetworkingCalls;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterShowEachItem extends RecyclerView.Adapter<RecyclerAdapterShowEachItem.ViewHolder> {

    Context context;
    List<Debt_Pojo> debtPojoList = new ArrayList<>();
    Activity activity;
    NetworkingCalls networkingCalls;
    String debitId;
    UserSharedPrefs userSharedPrefs;
    SharePrefs sharePrefs;

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
        holder.debitTakenDate.append(" "+debtPojoList.get(position).getDate().substring(0,11));

        networkingCalls = new NetworkingCalls(context,activity);
        userSharedPrefs= new UserSharedPrefs(context);
        sharePrefs = new SharePrefs(context);

        Boolean b = userSharedPrefs.isLoggedInUser();
        Boolean c = sharePrefs.isLoggedIn();

        //Toast.makeText(context, ""+b +" Admin also "+c, Toast.LENGTH_SHORT).show();
        if(sharePrefs.isLoggedIn()){
            holder.deleteItem.setVisibility(View.VISIBLE);

            debitId = debtPojoList.get(position).getDebtId();

            holder.deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {                //send debitI

                    new AlertDialog.Builder(context)
                            .setTitle("Delete Item")
                            .setMessage("Are you sure you want to delete this Item?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    networkingCalls.deleteItem(debitId);
                                    holder.constraintLayout.setVisibility(View.GONE);

                                    Toast.makeText(context, "Deleted! Item Added to history!", Toast.LENGTH_SHORT).show();
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                   /* networkingCalls.deleteItem(debitId);
                    holder.constraintLayout.setVisibility(View.GONE);

                    Toast.makeText(context, "Deleted! Item Added to history!", Toast.LENGTH_SHORT).show();*/
                }
            });
        }

        else if(userSharedPrefs.isLoggedInUser()){
            holder.deleteItem.setVisibility(View.GONE);
        }
        /*else {
            debitId = debtPojoList.get(position).getDebtId();

            holder.deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {                //send debitI

                    networkingCalls.deleteItem(debitId);
                    holder.constraintLayout.setVisibility(View.GONE);

                    Toast.makeText(context, "Deleted! Item Added to history!", Toast.LENGTH_SHORT).show();
                }
            });

        }*/
        /*debitId = debtPojoList.get(position).getDebtId();

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                //send debitI

                networkingCalls.deleteItem(debitId);
                holder.constraintLayout.setVisibility(View.GONE);

                Toast.makeText(context, "Deleted! Item Added to history!", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return debtPojoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView deleteItem;
        TextView ItemName,PriceOfOne,Total,Qty,debitTakenDate;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ItemName = itemView.findViewById(R.id.itemName);
            PriceOfOne = itemView.findViewById(R.id.priceOfOne);
            Total = itemView.findViewById(R.id.totalPrice);
            Qty = itemView.findViewById(R.id.qty);
            deleteItem = itemView.findViewById(R.id.delete);
            debitTakenDate= itemView.findViewById(R.id.debitTakeDate);
            constraintLayout = itemView.findViewById(R.id.constraintView2);
        }
    }
}
