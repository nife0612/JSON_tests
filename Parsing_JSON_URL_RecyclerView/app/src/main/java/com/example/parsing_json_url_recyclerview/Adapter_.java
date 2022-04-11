package com.example.parsing_json_url_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_ extends RecyclerView.Adapter<Adapter_.MyViewHolder> {

    private Context context;
    private List<MovieModelClass> movieListData;

    public Adapter_(Context context, List<MovieModelClass> movieListData) {
        this.context = context;
        this.movieListData = movieListData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_id.setText(movieListData.get(position).getId());
        holder.tv_name.setText(movieListData.get(position).getName());

        Glide.with(context).load(movieListData.get(position).getImage_link()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return movieListData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_id;
        TextView tv_name;
        ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv = itemView.findViewById(R.id.imageView);
        }
    }
}
