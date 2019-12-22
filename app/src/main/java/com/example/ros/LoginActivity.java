package com.example.ros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnLogin;
    EditText etUser;
    EditText etPass;
    String username;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUser = findViewById(R.id.editText);
        etPass = findViewById(R.id.editText2);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(findViewById(R.id.btnLogin) == v){
            username = etUser.getText().toString();
            pass = etPass.getText().toString();
            ROSApi rosApi = RetrofitClientInstance.getRetrofitInstance().create(ROSApi.class);
            Call<User> call = rosApi.userLogin(username, pass);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(!response.isSuccessful()){
                        return;
                    }

                    Intent i =  new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Failed",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
