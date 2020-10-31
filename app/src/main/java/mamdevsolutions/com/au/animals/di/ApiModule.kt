package mamdevsolutions.com.au.animals.di

import dagger.Module
import dagger.Provides
import mamdevsolutions.com.au.animals.model.AnimalApi
import mamdevsolutions.com.au.animals.model.AnimalApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net"

    @Provides
    fun provideAnimalApi(): AnimalApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // convertor factory json to POJO
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // (Rx java convert Objects into observable)
                .build()
                .create(AnimalApi::class.java)
    }

    @Provides
    fun provideAnimalApiService(): AnimalApiService {
        return  AnimalApiService()
    }
}