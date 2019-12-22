package com.example.ros;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    List<Product> orders;
    ArrayList<Orders> ordList;
    ListView lvOrders;
    TextView tvTotalPrice;
    TextView tblName;
    float totalPrice;
    String tableName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        lvOrders = view.findViewById(R.id.lv_orders);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        tblName = view.findViewById(R.id.tblName);
        return view;
    }

    public void getOrders(List<Product> orders, String tableName){
        this.orders = orders;
        this.tableName = tableName;
        ordList = new ArrayList<>();

        if(orders != null) {
            OrdersListAdapter ordersListAdapter = new OrdersListAdapter(getContext(), orders);
            lvOrders.setAdapter(ordersListAdapter);
            for(int i = 0 ; i < orders.size(); i++){
                totalPrice += Float.valueOf(orders.get(i).getPrice());
            }
            tvTotalPrice.setText(totalPrice+"€");
            tblName.setText(tableName);

            totalPrice = 0;
        }
    }

}
