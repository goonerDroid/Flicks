package com.sublime.core.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sublime.core.network.BuildConfig
import com.sublime.core.network.api.TmdbApiService
import com.sublime.core.network.datasource.RetrofitTmdbNetworkDataSource
import com.sublime.core.network.datasource.TmdbNetworkDataSource
import com.sublime.core.network.interceptor.AuthInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindTmdbNetworkDataSource(
        impl: RetrofitTmdbNetworkDataSource
    ): TmdbNetworkDataSource

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        @Provides
        @Singleton
        fun provideAuthInterceptor(): AuthInterceptor {
            return AuthInterceptor()
        }

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            authInterceptor: AuthInterceptor,
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(authInterceptor)

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(loggingInterceptor)
            }

            return builder.build()
        }

        @Provides
        @Singleton
        fun provideMoshi(): Moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Provides
        @Singleton
        fun provideTmdbApiService(
            retrofit: Retrofit
        ): TmdbApiService {
            return retrofit.create(TmdbApiService::class.java)
        }
    }
}