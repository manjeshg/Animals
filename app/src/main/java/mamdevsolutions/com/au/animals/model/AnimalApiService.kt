package mamdevsolutions.com.au.animals.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AnimalApiService {
    private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net"
    private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // convertor factory json to POJO
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // (Rx java convert Objects into observable)
            .build()
            .create(AnimalApi::class.java)

    fun getApiKey(): Single<ApiKey> {
        return api.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return api.getAnimals(key)
    }
}