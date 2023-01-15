package com.example.wisataappe020320099.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wisataappe020320099.R;
import com.example.wisataappe020320099.model.ModelMain;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ModelMain> items;
    private MainAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelMain mdlMain);
    }

    public MainAdapter(List<ModelMain> items, MainAdapter.onSelectData xSelectData) {
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelMain data = items.get(position);
        holder.imgMainData.setImageResource(data.getImgSrc());
        holder.tvMainData.setText(data.getTxtName());
        holder.cvMainData.setOnClickListener(v -> onSelectData.onSelected(data));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvMainData;
        public TextView tvMainData;
        public ImageView imgMainData;

        public ViewHolder(View itemView) {
            super(itemView);
            cvMainData = itemView.findViewById(R.id.cvMainData);
            tvMainData = itemView.findViewById(R.id.tvMainData);
            imgMainData = itemView.findViewById(R.id.imgMainData);
        }
    }
}