package com.keddits.data.source.repository

import com.keddits.data.source.base.BaseRemoteDataSource
import com.keddits.model.GameData
import io.reactivex.Single

internal class RepositoryRemoteDataSource : BaseRemoteDataSource(), RepositoryDataSource {

    override fun getRepositories(limit: Int): Single<GameData> =
            apiService.getGameData(limit)

    override fun saveRepositories(repositories: List<GameData>) {

    }

    override fun clearRepositories() {

    }

    override fun isEmpty(): Boolean = false
}
