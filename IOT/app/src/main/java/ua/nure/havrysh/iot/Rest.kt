package ua.nure.havrysh.iot

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object Rest {
    
    lateinit var easyGo: EasyGo
    
    fun init(serverAddress: String) {
        val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .baseUrl(serverAddress)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        easyGo = retrofit.create(EasyGo::class.java)
    }
    
    interface EasyGo {
        
        @POST("sensors")
        fun postSensorData(@Body sensors: SensorsRequest): Deferred<ResponseBody>
    }
}
