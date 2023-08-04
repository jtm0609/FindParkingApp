package com.jtmcompany.parkingapplication.hilt

import android.content.Context
import com.jtmcompany.data.api.ApiInterface
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSourceImpl
import com.jtmcompany.data.repository.ParkRepositoryImpl
import com.jtmcompany.repository.ParkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: ApiInterface, @ApplicationContext context: Context) : ParkRemoteDataSource{
        return ParkRemoteDataSourceImpl(apiInterface, context)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        parkRemoteDataSource: ParkRemoteDataSource
    ): ParkRepository{
        return ParkRepositoryImpl(parkRemoteDataSource)
    }
}