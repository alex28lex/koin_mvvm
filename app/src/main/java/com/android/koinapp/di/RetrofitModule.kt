package com.android.koinapp.di


import com.android.koinapp.web.RestClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val remoteWebSourceModule = module {
    single { provideUsersRestClient(retrofit = get()) }
    single {
        provideRetrofit(
            gson = get(),
            client = get()
        )
    }
    single { provideGson() }
    single { provideOkHttpClient(loggingInterceptor = get()) }
    single { provideHttpLoggingInterceptor() }
}


fun provideUsersRestClient(retrofit: Retrofit): RestClient {
    return retrofit.create(RestClient::class.java)
}


/*inline fun <reified T> provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}*/
fun provideRetrofit(
    gson: Gson,
    client: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .create()
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}


fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
