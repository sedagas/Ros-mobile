package com.example.ros;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TablesFragment extends Fragment {

    TextView tvTable;
    int tblListCount;
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.40.125.153:8091/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ROSApi rosApi = retrofit.create(ROSApi.class);

        Call<List<Tables>> call = rosApi.getTableDetail();

        call.enqueue(new Callback<List<Tables>>() {
            @Override
            public void onResponse(Call<List<Tables>> call, Response<List<Tables>> response) {
                if(!response.isSuccessful()){
                    tvTable.setText("Failed");
                    return;
                }
                List<Tables> tblList = response.body();

                tblListCount = tblList.size();

            }

            @Override
            public void onFailure(Call<List<Tables>> call, Throwable t) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tables, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpager);

        ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);

        Call<List<Tables>> call = rosApi.getTableDetail();
        call.enqueue(new Callback<List<Tables>>() {
            @Override
            public void onResponse(Call<List<Tables>> call, Response<List<Tables>> response) {
                viewPager.setAdapter(new FragmentPageAdapter(getFragmentManager(), getContext(), response.body() ));

            }

            @Override
            public void onFailure(Call<List<Tables>> call, Throwable t) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        return view;

    }
}
