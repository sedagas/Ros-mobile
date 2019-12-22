package com.example.ros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdditionAdapter extends BaseAdapter {


    List<Addition> additions;
    LayoutInflater inflater;
    Context context;

    public AdditionAdapter(Context context, List<Addition> additions){
        this.context = context;
        this.additions = additions;
    }

    @Override
    public int getCount() {
        return additions.size();
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
            convertView = inflater.inflate(R.layout.addition_row_item, null);
        }
        TextView tvTableName = convertView.findViewById(R.id.tvAdditionTableName);
        TextView tvCustomerName = convertView.findViewById(R.id.tvAdditionCustomerName);
        TextView tvPrice = convertView.findViewById(R.id.tvAdditionPrice);

        tvTableName.setText(additions.get(position).getTableName());
        tvCustomerName.setText(additions.get(position).getCustomerName());
        tvPrice.setText(additions.get(position).getTotal());

        return convertView;
    }
}
