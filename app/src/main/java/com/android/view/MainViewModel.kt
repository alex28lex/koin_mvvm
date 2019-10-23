package com.android.view

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.koinapp.model.IGitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(var repository: IGitRepository) : ViewModel() {
    val liveData = MutableLiveData<MainViewState>()

    @SuppressLint("CheckResult")
    fun loadUsers() {
        liveData.value = MainViewState.StateOnProgress
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = MainViewState.StateOnSuccess(it)
                },
                { t ->
                    t.printStackTrace()
                    liveData.value =
                        MainViewState.StateOnError(t.message ?: "unknown error")
                }
            )
    }
}