package com.example.ros;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ROSApi {


    @GET("Product/GetAllProducts")
    Call<List<Product>> getProduct();

    @GET("Tables/GetTableDetail")
    Call<List<Tables>> getTableDetail();

    @Headers("Content-Type: application/json")
    @POST("Addition/addAddition")
    Call<Addition> addAddition(@Body Addition jsonObject);

    @GET("Product/GetProducts/{id}")
    Call<List<Product>> getProducts(@Path("id") Integer id);

    @Headers("Content-Type: application/json")
    @POST("Addition/addOrders")
    Call<List<Product>> addOrders(@Body List<Product> orders);

    @GET("Addition/GetAddition")
    Call<List<Addition>> getAddition();

    @FormUrlEncoded
    @POST("Auth/UserLogin")
    Call<User> userLogin(@Field("username") String username, @Field("pass") String pass);
}
