package mamdevsolutions.com.au.animals.model

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AnimalApi {

    // gives key
    @GET("getKey")
    fun getApiKey(): Single<ApiKey>

    // gives animals with key as a parameter
    @POST("getAnimals")
    fun getAnimals(@Field("key") key: String): Single<List<Animal>>
}