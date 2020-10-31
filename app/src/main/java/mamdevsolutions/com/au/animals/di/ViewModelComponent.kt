package mamdevsolutions.com.au.animals.di

import dagger.Component
import mamdevsolutions.com.au.animals.util.SharedPreferencesHelper
import mamdevsolutions.com.au.animals.viewmodel.ListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}