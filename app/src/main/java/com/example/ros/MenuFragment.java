package com.example.ros;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    ListView lvCategories;
    ListView lvOrders;
    List<Product> products;
    ArrayList<Product> orders;
    ArrayList<Orders> ordList;
    GridView gridView;
    Product newPrd;
    float totalPrice;
    OrderActivity orderActivity;
    MenuFragmentListener listener;

    public interface MenuFragmentListener{
        void onInputMenuSent(List<Product> orders);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        lvCategories = view.findViewById(R.id.lv_product_category);
        lvOrders = view.findViewById(R.id.lv_orders);
        gridView = view.findViewById(R.id.gv_products);
        orderActivity = new OrderActivity();

        getCategories();
        return view;

    }

    public void getCategories()
    {

        List<String> values = new ArrayList<>();
        orders = new ArrayList<>();
        for(int i = 0 ; i < Category.values().length ; i ++){
            values.add(String.valueOf(Category.values()[i]));
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_category,R.id.tv_categories, values);

        lvCategories.setAdapter(adapter);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getProducts((position));

            }
        });
    }

    public void getProducts(int id){
        ordList = new ArrayList<>();
        final ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);
        Call<List<Product>> call = rosApi.getProducts(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                final ProductAdapter adapter = new ProductAdapter(getContext(), products);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        newPrd = new Product();
                        newPrd.setName(products.get(position).getName());
                        newPrd.setPrice(products.get(position).getPrice());
                        orders.add(newPrd);
                        orderActivity.getOrderList(orders);
                        listener.onInputMenuSent(orders);
                        addOrders(orders);

                        Toast.makeText(getContext(),"prd: " ,Toast.LENGTH_LONG).show();
                    }
                });

            }


            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }


    public void addOrders(List<Product> orders){

        final ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);
        Call<List<Product>> call = rosApi.addOrders(orders);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MenuFragmentListener){
            listener = (MenuFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement MenuFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
