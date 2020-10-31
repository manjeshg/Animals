package mamdevsolutions.com.au.animals.model

import io.reactivex.Single
import mamdevsolutions.com.au.animals.di.DaggerApiComponent
import javax.inject.Inject

class AnimalApiService {

    // we will put this into DI class and inject
    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getApiKey(): Single<ApiKey> {
        return api.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return api.getAnimals(key)
    }
}