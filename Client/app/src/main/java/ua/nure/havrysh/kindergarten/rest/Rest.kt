package ua.nure.havrysh.kindergarten.rest

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    
    val SERVER_URL = "https://thawing-cove-80542.herokuapp.com/"
    
    @SuppressLint("StaticFieldLeak")
    lateinit var accessTokenStorage: AccessTokenStorage
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .client(client())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat(
                    "yyyy-MM-dd"
                ).create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    
    private val restController = retrofit.create(Controller::class.java)
    
    private fun client(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val authTokenInjector = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = accessTokenStorage.getToken()
                if (token.isEmpty()) {
                    return chain.proceed(chain.request())
                }
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(request)
            }
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(authTokenInjector)
            .addInterceptor(logger)
            .build()
    }
    
    fun get(): Controller {
        return restController
    }
}
