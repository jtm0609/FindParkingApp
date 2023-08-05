package com.jtmcompany.parkingapplication.hilt

import android.util.Log
import android.util.Log.d
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jtmcompany.data.api.ApiClient
import com.jtmcompany.data.api.ApiInterface
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()

        return Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Rx도 사용하기 때문에 추가 필요.
            .addConverterFactory(TikXmlConverterFactory.create(parser))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        LoggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60,TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(LoggerInterceptor)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor{ message->
            Log.d("tak",message)
        }.let{
            if(BuildConfig.DEBUG){
                com.orhanobut.logger.Logger.addLogAdapter(AndroidLogAdapter())
                it.setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                it.setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
    }
}