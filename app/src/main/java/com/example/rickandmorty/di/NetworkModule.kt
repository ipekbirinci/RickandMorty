package com.example.rickandmorty.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttp: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttp)
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(60.toLong(), TimeUnit.SECONDS)
            .readTimeout(60.toLong(), TimeUnit.SECONDS)
            .writeTimeout(60.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()
}

