package com.example.readquizapp.api;

import com.example.readquizapp.model.ApiResponse;
import com.example.readquizapp.model.Reading;
import com.example.readquizapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    // Auth
    @POST("users/register")
    Call<ApiResponse<User>> register(@Body User user);

    @POST("users/login")
    Call<ApiResponse<User>> login(@Body User user);

    // Readings
    @GET("readings")
    Call<ApiResponse<List<Reading>>> getAllReadings();

    @GET("readings/{id}")
    Call<ApiResponse<Reading>> getReadingById(@Path("id") Integer id);

    @POST("readings")
    Call<ApiResponse<Reading>> createReading(@Body Reading reading);

    @PUT("readings/{id}")
    Call<ApiResponse<Reading>> updateReading(@Path("id") Integer id, @Body Reading reading);

    @DELETE("readings/{id}")
    Call<ApiResponse<Void>> deleteReading(@Path("id") Integer id);
}
