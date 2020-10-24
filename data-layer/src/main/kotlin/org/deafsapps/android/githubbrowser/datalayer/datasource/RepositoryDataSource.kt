package org.deafsapps.android.githubbrowser.datalayer.datasource

import arrow.core.Either
import org.deafsapps.android.githubbrowser.datalayer.domain.dataRepoListToBo
import org.deafsapps.android.githubbrowser.datalayer.domain.dtoToBo
import org.deafsapps.android.githubbrowser.datalayer.service.GithubApiService
import org.deafsapps.android.githubbrowser.datalayer.utils.safeCall
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import retrofit2.Retrofit
import javax.inject.Inject

interface RepositoryDataSource {

    companion object {
        const val REPOSITORY_DATA_SOURCE_TAG = "repositoryDataSource"
        const val API_BASE_URL = "https://api.github.com"
    }

    suspend fun fetchDataRepositoriesResponse(): Either<FailureBo, List<DataRepoBo>>

}

class GithubDataSource @Inject constructor(private val retrofit: Retrofit) : RepositoryDataSource {

    override suspend fun fetchDataRepositoriesResponse(): Either<FailureBo, List<DataRepoBo>> =
        retrofit.create(GithubApiService::class.java).fetchPopularGithubReposAsync()
            .safeCall(transform = { it.dataRepoListToBo() })

}