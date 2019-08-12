package br.com.rafaeldias.gym.injection.module

import br.com.rafaeldias.gym.network.GymApi
import br.com.rafaeldias.gym.utils.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    val okhttp = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGymApi(retrofit: Retrofit): GymApi {
        return retrofit.create(GymApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okhttp)
            .build()
    }
}