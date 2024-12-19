package com.jtmcompany.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jtmcompany.data.api.ApiInterface
import com.jtmcompany.data.datasource.local.ParkLocalDataSource
import com.jtmcompany.data.datasource.local.ParkLocalDataSourceImpl
import com.jtmcompany.data.datasource.local.PrefDataSource
import com.jtmcompany.data.datasource.local.PrefDataSourceImpl
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSourceImpl
import com.jtmcompany.data.db.ParkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(parkDao: ParkDao): ParkLocalDataSource {
        return ParkLocalDataSourceImpl(parkDao)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiInterface: ApiInterface,
        @ApplicationContext context: Context
    ): ParkRemoteDataSource {
        return ParkRemoteDataSourceImpl(apiInterface, context)
    }

    @Singleton
    @Provides
    fun providePrefDataSource(pref : SharedPreferences): PrefDataSource {
        return PrefDataSourceImpl(pref)
    }

}