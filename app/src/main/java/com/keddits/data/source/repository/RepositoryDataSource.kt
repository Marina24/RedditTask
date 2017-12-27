package com.keddits.data.source.repository

import com.keddits.model.GameData
import io.reactivex.Single

internal interface RepositoryDataSource {

    fun getRepositories(limit: Int): Single<GameData>

    fun saveRepositories(repositories: List<GameData>)

    fun clearRepositories()

    fun isEmpty(): Boolean

}