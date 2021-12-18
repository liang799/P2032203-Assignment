package com.sp.p2032203assignment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String data1[], data2[];
    private int images[];
    private Context context;

    public CustomAdapter(Context ct, String[] s1, String[] s2, int[] img) {
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
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
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView myImage;

        public ViewHolder(View view) {
            super(view);
            myText1 = itemView.findViewById(R.id.textView11);
            myText2 = itemView.findViewById(R.id.textView12);
            myImage = itemView.findViewById(R.id.imageView3);
        }

    }
}

