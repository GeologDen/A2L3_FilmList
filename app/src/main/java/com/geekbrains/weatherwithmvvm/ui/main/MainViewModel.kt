package com.geekbrains.weatherwithmvvm.ui.main

import androidx.lifecycle.*
import com.geekbrains.weatherwithmvvm.model.AppState
import com.geekbrains.weatherwithmvvm.model.Repository
import com.geekbrains.weatherwithmvvm.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData())
    : ViewModel(), LifecycleObserver {
    private val repository: Repository = RepositoryImpl()
    private val lifeCycleLiveData = MutableLiveData<String>()

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    fun getData(): LiveData<AppState> {
        getWeatherFromLocalSourceRus()
        return liveDataToObserve
    }

    fun getLifeCycleData() = lifeCycleLiveData

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(
                    if (isRussian) repository.getWeatherFromLocalStorageRus()
                    else repository.getWeatherFromLocalStorageWorld())
            )
            //liveDataToObserve.postValue(AppState.Error())
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        lifeCycleLiveData.value = "Start"
    }
}