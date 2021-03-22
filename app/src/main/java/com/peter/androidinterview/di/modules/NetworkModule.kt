package com.peter.androidinterview.di.modules

import com.peter.androidinterview.apis.Api
import com.peter.androidinterview.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.peter.androidinterview.di.App

/**
 * Network module to provide a Retrofit instance into the [App] component.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provide the [OkHttpClient] which will in turn be used when providing the [Retrofit] instance.
     * Returns the [OkHttpClient] instance.
     */
    @Provides
    fun providesOkHttpClient(): OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    /**
     * Provide a [Retrofit] instance to be used when performing network calls.
     * It uses an [OkHttpClient] to improve network operations.
     * Returns the [Retrofit] instance.
     */
    @Provides
    fun providesRetrofitInstance(client: OkHttpClient): Retrofit{
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder.build()
    }

    /**
     * Provide the [Api] which uses a [Retrofit] instance to make network calls
     */
    @Provides
    fun providesApi(retrofit: Retrofit): Api{
        return  retrofit.create(Api::class.java)
    }
}