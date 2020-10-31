package mamdevsolutions.com.au.animals.di

import dagger.Component
import mamdevsolutions.com.au.animals.model.AnimalApiService

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)
}