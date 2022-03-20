package com.example.libraskids.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.libraskids.Model.Item;
import com.example.libraskids.R;
import com.example.libraskids.Activity.DetailsItemActivity;

import java.util.ArrayList;


public class AdapterDetailsCategory extends RecyclerView.Adapter<AdapterDetailsCategory.MyViewHolder> {

    public Context mContext;
    private ArrayList<Item> mData;

    public AdapterDetailsCategory(Context mContext, ArrayList<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_num,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //get data
        Item item = mData.get(position);

        //set data
        Glide.with(holder.itemNum.getContext()).load(item.getImageUrl()).into(holder.itemNum);

        //Evento de clique para outra acticity
        holder.CardViewNum.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, DetailsItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", mData);
            bundle.putInt("position",position);
            intent.putExtras(bundle);

            //iniciando a activity
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size(); // retorna o tamanho da lista
    }

    //ViewHolder Classe , inicia ui
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView itemNum;
        VideoView videov;
        CardView CardViewNum ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //view do item_num
            itemNum = itemView.findViewById(R.id.itemNum);
            videov =  itemView.findViewById(R.id.videov);
            CardViewNum = itemView.findViewById(R.id.CardViewNum);
        }
    }
}
