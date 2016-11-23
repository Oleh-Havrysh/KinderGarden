package ua.nure.havrysh.kindergarten.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg on 22.11.2016.
 */

public class Rest {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.13:8080/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .build();

    private static final Controller restController = retrofit.create(Controller.class);

    public static Controller get() {
        return restController;
    }
}
