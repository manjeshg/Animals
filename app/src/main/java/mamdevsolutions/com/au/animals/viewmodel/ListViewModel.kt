package mamdevsolutions.com.au.animals.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import mamdevsolutions.com.au.animals.model.Animal
import mamdevsolutions.com.au.animals.model.AnimalApiService
import mamdevsolutions.com.au.animals.model.ApiKey

class ListViewModel(application: Application) : AndroidViewModel(application) {

    // Lazy -> system is not going to instantiate until and unless its needed
    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean> ()}
    val loading by lazy { MutableLiveData<Boolean>()}

    private val disposable = CompositeDisposable()
    private val apiService = AnimalApiService()
    fun refresh() {
        loading.value = true
        getKey()
    }

    private fun getKey() {
        // create disposable
        disposable.add(
            apiService.getApiKey() //  this operation
               .subscribeOn(Schedulers.newThread()) // we want to do it on new thread
                    .observeOn(AndroidSchedulers.mainThread()) // get result on the main thread
                    .subscribeWith(object: DisposableSingleObserver<ApiKey>() {
                        override fun onSuccess(apikey: ApiKey) {
                            if (apikey.key.isNullOrEmpty()) {
                                loadError.value = true
                                loading.value = false
                            } else {
                                getAnimals(apikey.key)
                            }
                        }

                        override fun onError(e: Throwable) {
                            loadError.value = true
                            loading.value = false
                        }

                    })
        )
    }

    private fun getAnimals(key: String) {
        disposable.add (
                apiService.getAnimals(key)
                        .subscribeOn(Schedulers.newThread()) // we want to do it on new thread
                        .observeOn(AndroidSchedulers.mainThread()) // get result on the main thread
                        .subscribeWith(object: DisposableSingleObserver<List<Animal>>() {
                            override fun onSuccess(list: List<Animal>) {
                                loading.value = false
                                loadError.value = false
                                animals.value = list
                            }

                            override fun onError(e: Throwable) {
                                loadError.value = true
                                loading.value = false
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}