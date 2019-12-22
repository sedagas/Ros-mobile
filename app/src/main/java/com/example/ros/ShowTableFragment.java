package com.example.ros;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.List;

@SuppressLint("ValidFragment")
public class ShowTableFragment extends Fragment {

    GridView gridView;
    List<Tables> tablesList;
    int pos;
    public ShowTableFragment(List<Tables> tableList, int position){
        this.tablesList = tableList;
        this.pos = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_tables, container, false);
        gridView = view.findViewById(R.id.gv_tables);
        TableAdapter adapter = new TableAdapter(getContext(), tablesList, pos);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tableName = tablesList.get(pos).getName() + " " + (position + 1);
                openDialog(tableName);

            }
        });

        return view;
    }

    private void openDialog(final String tableName){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View subView = inflater.inflate(R.layout.diyalog_layout, null);
        final EditText subEditText = subView.findViewById(R.id.et_dialog);
        final Button  btnStartAddition = subView.findViewById(R.id.btn_start_addition);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(tableName);
        builder.setMessage("Enter Customer's Name");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        btnStartAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  customerName = String.valueOf(subEditText.getText());
                Intent i = new Intent(getContext(), OrderActivity.class);
                i.putExtra("CustomerName", customerName);
                i.putExtra("TableName", tableName);
                startActivity(i);
            }
        });

        alertDialog.show();

    }
}
