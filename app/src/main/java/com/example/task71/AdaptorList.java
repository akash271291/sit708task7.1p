package com.example.task71;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class AdaptorList extends RecyclerView.Adapter<AdaptorList.MyViewHolder> {

    List<Item> items;
    Context mContext;

    public AdaptorList(Context mContext, List<Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptorList.MyViewHolder holder, int position) {

        holder.typeTextView.setText(items.get(position).getType() + " Item");
        holder.descriptionTextView.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @NonNull
    @Override
    public AdaptorList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.showallrecyclerview_layout, parent, false);

        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView descriptionTextView, typeTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment itemFragment = new FirstFragment();

            Bundle args = new Bundle();
            int position = getLayoutPosition();
            args.putSerializable("data", (Serializable) items.get(position));
            args.putInt("position", position);

            itemFragment.setArguments(args);


            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, itemFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}

