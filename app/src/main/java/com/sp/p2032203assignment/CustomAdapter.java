package com.sp.p2032203assignment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Cursor cursor;
    private Context context;

    public CustomAdapter(Context ct, Cursor c) {
        context = ct;
        cursor = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.address.setText(cursor.getString(2));
        holder.pack.setImageURI(Uri.parse(cursor.getString(3)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        ImageView pack;

        public ViewHolder(View view) {
            super(view);
            address = itemView.findViewById(R.id.address_bind_me);
            pack = itemView.findViewById(R.id.package_bind_me);
        }

    }
}

