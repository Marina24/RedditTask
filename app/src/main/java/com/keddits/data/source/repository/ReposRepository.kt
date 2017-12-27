package com.keddits.data.source.repository

import com.keddits.intarfaces.Manager
import com.keddits.model.GameData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class ReposRepository : Manager {

    private val remoteSource: RepositoryRemoteDataSource = RepositoryRemoteDataSource().apply {
        init()
    }

    fun getRepositories(limit: Int, local: Boolean): Observable<GameData> {
        return getRemoteRepositories(limit).toObservable()
    }

    private fun getRemoteRepositories(limit: Int): Single<GameData> =
            remoteSource.getRepositories(limit)
                    .doOnSuccess { repositories -> saveRepositories(repositories) }
                    .observeOn(AndroidSchedulers.mainThread())

    private fun saveRepositories(repositories: GameData) {

    }

    fun clearRepositories() {

    }

    override fun clear() {

    }
}