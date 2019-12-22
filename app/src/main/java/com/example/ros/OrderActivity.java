package com.example.ros;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements MenuFragment.MenuFragmentListener {

    OrderFragment orderFragment;
    MenuFragment menuFragment;
    List<Product> orderList;
    ArrayList<Orders> ordersArrayList;
    String tableName;
    String customerName;
    float totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ordersArrayList = new ArrayList<>();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        addAddition();

        orderFragment = new OrderFragment();
        menuFragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_orders, orderFragment)
                .add(R.id.container_menu, menuFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                saveOrdes();
                break;
            case R.id.finish:
                finishOrder();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addAddition(){

        Intent i = getIntent();
        customerName = i.getStringExtra("CustomerName");
        tableName = i.getStringExtra("TableName");

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        Addition addition = new Addition();
        addition.setUid(1);
        addition.setCustomerName(customerName);
        addition.setTableName(tableName);
        addition.setDate(currentDate);
        addition.setState("0");
        addition.setStatus("2");


        ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);
        Call<Addition> call = rosApi.addAddition(addition);
        call.enqueue(new Callback<Addition>() {
            @Override
            public void onResponse(Call<Addition> call, Response<Addition> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(OrderActivity.this,"Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Addition> call, Throwable t) {
                Toast.makeText(OrderActivity.this,"failed adding addition", Toast.LENGTH_LONG).show();

            }
        });
        Toast.makeText(this, "Addition Id: " + addition.getId(),Toast.LENGTH_LONG).show();

    }

    private  void finishOrder(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.diyalog_finish_layout, null);
        Button btnPay = subView.findViewById(R.id.btn_pay);
        Button btnDebt = subView.findViewById(R.id.btn_debt);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tableName);
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        alertDialog.show();
    }

    private void saveOrdes(){

        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.diyalog_save_layout, null);
        Button btnSave = subView.findViewById(R.id.btn_save);
        Button btnBack = subView.findViewById(R.id.btn_back);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tableName);
        builder.setView(subView);
        final AlertDialog alertDialog = builder.create();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        alertDialog.show();

    }

    public void getOrderList(List<Product> orders){
        this.orderList = orders;
        for(int i =0  ; i <orders.size(); i++){
            totalPrice += Float.valueOf(orders.get(i).getPrice());

        }

        totalPrice = 0;
    }

    @Override
    public void onInputMenuSent(List<Product> orders) {
        orderFragment.getOrders(orders,tableName);
    }
}
