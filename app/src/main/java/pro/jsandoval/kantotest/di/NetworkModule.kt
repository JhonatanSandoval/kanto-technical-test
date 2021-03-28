package pro.jsandoval.kantotest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.jsandoval.kantotest.BuildConfig
import pro.jsandoval.kantotest.data.remote.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().apply {
            excludeFieldsWithoutExposeAnnotation()
            serializeNulls()
            setLenient()
        }.create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder().apply {
                    addHeader("Accept", "application/json")
                }
                chain.proceed(requestBuilder.build())
            }
            addInterceptor(interceptor)
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    companion object {
        private const val DEFAULT_TIMEOUT = 30L
    }
}