package com.example.ros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TableAdapter extends BaseAdapter {

    List<Tables> tablesList;
    LayoutInflater inflater;
    Context context;
    int pos;

    public  TableAdapter(Context context, List<Tables> tablesList, int position){
        this.context = context;
        this.tablesList = tablesList;
        this.pos = position;

    }
    @Override
    public int getCount() {
        return tablesList.get(pos).getCount();
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
            convertView = inflater.inflate(R.layout.table_row_item, null);
        }

        TextView tv = convertView.findViewById(R.id.tv_table_name);
        tv.setText(tablesList.get(pos).getName()+ " " + (position + 1));

        return convertView;
    }
}
