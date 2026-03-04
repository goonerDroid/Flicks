package com.sublime.core.network.interceptor

import com.sublime.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.TMDB_API_TOKEN}"
            )
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}