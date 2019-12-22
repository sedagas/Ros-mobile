package com.example.ros;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrdersListAdapter extends ArrayAdapter<Product> {

    static int piece =1;

    Context context;
    private List<Product> orders;
    public OrdersListAdapter(Context context, List<Product> orders){
        super(context, R.layout.list_addition_detail, orders);
        this.context = context;
        this.orders = orders;




    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.list_addition_detail, parent, false);

        TextView tvPrice = convertView.findViewById(R.id.tv_order_price);
        TextView tvName = convertView.findViewById(R.id.tv_product);
        tvPrice.setText(orders.get(position).getPrice());
        tvName.setText(orders.get(position).getName());

        return convertView;

    }
}
