package com.example.a8_1c;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a8_1c.model.UserPlaylist;

import java.util.List;

public class playlistAdaptor extends RecyclerView.Adapter<playlistAdaptor.viewHolder> {
    List<UserPlaylist> itemList;
    Context context;
    ItemClickListener clickListener;


    public playlistAdaptor(List <UserPlaylist> itemList, Context context,ItemClickListener clickListener)
    {
        this.itemList = itemList;
        this.context = context;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.urldetail,parent,false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UserPlaylist item = itemList.get(position);
        //Log.d("URL", item.getUrl());
        holder.url.setText(item.getUrl());

        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(itemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView url;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.urlData);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(UserPlaylist item);
    }
}
