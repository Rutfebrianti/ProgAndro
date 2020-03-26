package com.example.progandro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<MovieRecyclerItem> movieRecyclerItems;

    public RecyclerViewAdapter(Context mContext, List<MovieRecyclerItem> movieRecyclerItemList) {
        this.mContext = mContext;
        this.movieRecyclerItems = movieRecyclerItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.judul.setText(movieRecyclerItems.get(position).getmTitle());
        holder.descMovie.setText(movieRecyclerItems.get(position).getmDescription());
        holder.image.setImageResource(movieRecyclerItems.get(position).getmImageResource());
    }

    @Override
    public int getItemCount() {
        return movieRecyclerItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView judul;
        private TextView descMovie;
        private ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.textViewTitle);
            descMovie =  itemView.findViewById(R.id.textViewDesc);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
