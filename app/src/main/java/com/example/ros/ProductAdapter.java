package com.example.ros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    List<Product> products;
    LayoutInflater inflater;
    Context context;

    public ProductAdapter(Context context, List<Product> products){
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null ){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.product_row_item, null);
        }

        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        TextView tvProductName = convertView.findViewById(R.id.tv_product_name);

        tvPrice.setText(products.get(position).getPrice());
        tvProductName.setText(products.get(position).getName());

        return convertView;
    }
}
