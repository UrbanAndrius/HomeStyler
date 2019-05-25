package com.andrius.homestyler.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrius.homestyler.R;
import com.andrius.homestyler.entity.Furniture;
import com.andrius.homestyler.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.Holder> {

    private List<Furniture> furnitureList = new ArrayList<>();
    private OnFurnitureClick furnitureClick;

    public interface OnFurnitureClick {
        void onFurnitureClick(Furniture furniture);
    }

    FurnitureAdapter(OnFurnitureClick furnitureClick) {
        this.furnitureClick = furnitureClick;
    }

    void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holder(inflater.inflate(R.layout.item_furniture, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Furniture furniture = furnitureList.get(position);
        holder.itemView.setOnClickListener(view -> furnitureClick.onFurnitureClick(furniture));
        holder.tvColor.setText(furniture.getColor().toUpperCase());
        holder.tvPrice.setText(furniture.getFormatedPrice());
        holder.tvType.setText(furniture.getType());
        holder.ivPicture.setImageBitmap(ImageUtil.getBitmap(furniture.getImageBase64()));
    }

    @Override
    public int getItemCount() {
        return furnitureList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvColor)
        TextView tvColor;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.ivPicture)
        ImageView ivPicture;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
