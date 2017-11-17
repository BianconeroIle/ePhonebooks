package com.example.ilijaangeleski.phonebook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ilijaangeleski.phonebook.R;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.util.CircleTransform;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<User> items;
    private Context context;
    private int layoutResourceId;

    public RecyclerViewAdapter(List<User> items, Context context, int layoutResourceId) {
        this.items = items;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        User current = items.get(position);
        holder.username.setText(current.getLogin().getUsername());
        holder.phone.setText(current.getPhone());

        Picasso.with(context).load(current.getPicture().getLarge())
                .transform(new CircleTransform()).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round)
                .into(holder.userPhoto);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView phone;
        ImageView userPhoto;
        public MyViewHolder(View itemView) {
            super(itemView);
            username = (TextView)itemView.findViewById(R.id.username);
            phone = (TextView)itemView.findViewById(R.id.phoneNumber);
            userPhoto = (ImageView) itemView.findViewById(R.id.profileImage);

        }
    }
}
