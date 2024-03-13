package com.imtiaz_acedamy.finance_dashboad_android.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imtiaz_acedamy.finance_dashboad_android.Domain.domain;
import com.imtiaz_acedamy.finance_dashboad_android.R;
import com.majorik.sparklinelibrary.SparkLineLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.Viewhollder> {

    ArrayList<domain> dataList;
    DecimalFormat formatter;

    public CryptoAdapter(ArrayList<domain> dataList) {
        this.dataList = dataList;
        formatter = new DecimalFormat("###,###,###,###.##");
    }

    @NonNull
    @Override
    public CryptoAdapter.Viewhollder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_crypto, parent, false);
        return new Viewhollder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.Viewhollder holder, int position) {
        holder.nameTxt.setText(dataList.get(position).getName() + "");
        holder.priceTxt.setText("$" + formatter.format(dataList.get(position).getPrice()));
        holder.changePercentTxt.setText(dataList.get(position).getChangePercent() + "%");
        holder.propertyAmountTxt.setText("$" + dataList.get(position).getPropertyAmount());
        holder.propertySizeTxt.setText(dataList.get(position).getPropertySize() + dataList.get(position).getSymbol());
        holder.lineChart.setData(dataList.get(position).getLineData());

        if (dataList.get(position).getChangePercent() > 0) {

            holder.changePercentTxt.setTextColor(Color.parseColor("#12c737"));
            holder.lineChart.setSparkLineColor(Color.parseColor("#12c737"));

        } else if (dataList.get(position).getChangePercent() < 0) {

            holder.changePercentTxt.setTextColor(Color.parseColor("#fc0000"));
            holder.lineChart.setSparkLineColor(Color.parseColor("#fc0000"));

        } else {

            holder.changePercentTxt.setTextColor(Color.parseColor("#ffffff"));
            holder.lineChart.setSparkLineColor(Color.parseColor("#ffffff"));

        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(dataList.get(position).getName(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Viewhollder extends RecyclerView.ViewHolder {

        TextView nameTxt, priceTxt, changePercentTxt, propertySizeTxt, propertyAmountTxt;
        ImageView logo;
        SparkLineLayout lineChart;

        public Viewhollder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.cryptoNameTxt);
            priceTxt = itemView.findViewById(R.id.cryptoPrice);
            changePercentTxt = itemView.findViewById(R.id.changePercentTxt);
            propertySizeTxt = itemView.findViewById(R.id.propertySizeTxt);
            propertyAmountTxt = itemView.findViewById(R.id.propertyAmountTxt);
            logo = itemView.findViewById(R.id.logoImg);
            lineChart = itemView.findViewById(R.id.sparkLineLayout);

        }
    }
}
