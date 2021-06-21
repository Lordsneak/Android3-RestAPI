package com.example.apirest.Utils;

import com.example.apirest.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("users")
    Call<List<UserModel>> getUsers();

    @POST("users")
    Call<UserModel> addUser(@Body UserModel user);

    @PUT("users/{userId}")
    Call<UserModel> updateUser(@Body UserModel user, @Path("userId") String userId);

    @DELETE("users/{userId}")
    Call<UserModel> deleteUser(@Path("userId") String userId);

}
