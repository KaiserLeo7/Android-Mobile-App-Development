package com.example.donationappv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.TasksViewHolder> implements Filterable {

    Context context;
    List<Donations> donationsList;
    List<Donations> donationsListFull;

    public DonationAdapter(Context appContext, List<Donations> donationsListNew){
        this.context = appContext;
        this.donationsList = donationsListNew;
        donationsListFull = new ArrayList<>(donationsList);
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_donations, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {

        Donations donations = donationsList.get(position);

        holder.DonationsId.setText(String.valueOf(donations.getId()));
        holder.Amount.setText(String.valueOf(donations.getAmount()));

        int valuePaymentMethod = donations.getPaymentMethod();

        if (valuePaymentMethod == 1 )
            holder.PaymentMethod.setText("Credit Card");
        else
            holder.PaymentMethod.setText("PayPal");
    }

    @Override
    public int getItemCount() {
        return donationsList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

   private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Donations> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(donationsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Donations donation : donationsListFull) {
                    String amountExample = String.valueOf(donation.getAmount());
                    if (amountExample.toLowerCase().contains(filterPattern)) {
                        filteredList.add(donation);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            donationsList.clear();
            donationsList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
   };

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView DonationsId, Amount, PaymentMethod;

        public TasksViewHolder(View itemView) {
            super(itemView);

            DonationsId = itemView.findViewById(R.id.DonationsId);
            Amount = itemView.findViewById(R.id.Amount);
            PaymentMethod = itemView.findViewById(R.id.PaymentMethod);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
