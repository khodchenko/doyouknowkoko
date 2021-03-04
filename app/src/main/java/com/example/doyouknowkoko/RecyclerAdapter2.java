package com.example.doyouknowkoko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    private static final String TAG = "RecyclerView";
    private Context context;
    private ArrayList<ReceiveData> receiveDataArrayList;

    public RecyclerAdapter2(Context context, ArrayList<ReceiveData> receiveDataArrayList) {
        this.context = context;
        this.receiveDataArrayList = receiveDataArrayList;
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView outfitName;
        TextView outfitBrand;
        TextView outfitSize;
        TextView outfitComment;
        TextView outfitPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            outfitName = itemView.findViewById(R.id.tv_name);
            outfitBrand = itemView.findViewById(R.id.tv_brand);
            outfitSize = itemView.findViewById(R.id.tv_size);
            outfitComment = itemView.findViewById(R.id.tv_comment);
            outfitPrice = itemView.findViewById(R.id.tv_price);

        }
    }


    @NonNull
    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_item, parent, false);





        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.outfitName.setText(receiveDataArrayList.get(position).getOutfitName());

    }



    @Override
    public int getItemCount() {
        return 0;
    }
}
