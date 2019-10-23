package com.android.koinapp.model

import io.reactivex.Flowable

/**
 * Developed by Magora Team (magora-systems.com). 2018.
 *
 * @author mihaylov
 */
interface IGitRepository {
    fun getUsers(): Flowable<List<UserModel>>
}