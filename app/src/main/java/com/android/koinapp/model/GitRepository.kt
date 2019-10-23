package com.android.koinapp.model

import com.android.koinapp.web.RestClient
import io.reactivex.Flowable


class GitRepository(private val restClient: RestClient) : IGitRepository {
    override fun getUsers(): Flowable<List<UserModel>> {
        return restClient.getUsers()
    }
}