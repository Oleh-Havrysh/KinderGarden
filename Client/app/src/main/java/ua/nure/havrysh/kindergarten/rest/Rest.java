package ua.nure.havrysh.kindergarten.rest;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg on 22.11.2016.
 */

public class Rest {

    static final String SERVER_URL = "http://10.0.2.2:8080/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .build();

    private static final Controller restController = retrofit.create(Controller.class);

    public static Controller get() {
        return restController;
    }
}
