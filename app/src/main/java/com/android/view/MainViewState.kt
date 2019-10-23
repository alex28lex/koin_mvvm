package com.android.view

import com.android.koinapp.model.UserModel


sealed class MainViewState {
    object StateOnProgress : MainViewState()
    class StateOnSuccess(var users: List<UserModel>) : MainViewState()
    class StateOnError(var msg: String) : MainViewState()
}