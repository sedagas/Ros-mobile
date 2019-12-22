package com.example.ros;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdditionsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additions, container, false);
        final ListView lvAdditions = view.findViewById(R.id.lv_additions);
        ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);
        Call<List<Addition>> call = rosApi.getAddition();
        call.enqueue(new Callback<List<Addition>>() {
            @Override
            public void onResponse(Call<List<Addition>> call, Response<List<Addition>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Addition> additions = response.body();
                AdditionAdapter adapter = new AdditionAdapter(getContext(), additions);
                lvAdditions.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Addition>> call, Throwable t) {

            }
        });
        return view;
    }
}
