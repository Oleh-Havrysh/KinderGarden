package ua.nure.havrysh.iot;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oleg on 25.12.2016.
 */

public class Rest {
    public static EasyGo easyGo;

    public static void init(String serverAddress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        easyGo = retrofit.create(EasyGo.class);

    }

    public interface EasyGo {
        @POST("sensor")
        Call<Boolean> postSensorData(@Query("id") long id, @Query("gates") String gates, @Query("sensorData") int sensorData);
    }

}
