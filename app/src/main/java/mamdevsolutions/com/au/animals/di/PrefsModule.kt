package mamdevsolutions.com.au.animals.di

import android.app.Activity
import android.app.Application
import dagger.Module
import dagger.Provides
import mamdevsolutions.com.au.animals.util.SharedPreferencesHelper
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class PrefsModule {

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_APP)
    fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_ACTIVITY)
    fun provideActivitySharedPreferences(activity: Activity): SharedPreferencesHelper {
        return SharedPreferencesHelper(activity)
    }
}

const val CONTEXT_APP = "Application context"
const val CONTEXT_ACTIVITY = "Activity context"

@Qualifier
annotation class TypeOfContext(val type: String)