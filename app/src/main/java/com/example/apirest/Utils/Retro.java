package com.example.apirest.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    public static Retrofit getClient(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return  retrofit;
    }

    public static class Api {

        public static final String URL_001 = "http://192.168.10.24:8080/" ; // School Api
        public static final String URL_002 = "http://192.168.10.24:8080/"  ; // Home Api

        public static UserService getUserService() {
            return  getClient(URL_001).create(UserService.class);
        }
    }
}
