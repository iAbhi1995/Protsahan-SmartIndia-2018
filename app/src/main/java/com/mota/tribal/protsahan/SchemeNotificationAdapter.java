package com.mota.tribal.protsahan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhi on 22/3/18.
 */

public class SchemeNotificationAdapter extends RecyclerView.Adapter<SchemeNotificationAdapter.NotificationViewHolder> {


    private Context mContext;
    private ArrayList<String> titles;

    public SchemeNotificationAdapter(Context mContext, ArrayList<String> titles) {
        this.mContext = mContext;
        this.titles = titles;
    }


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
        return new SchemeNotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
