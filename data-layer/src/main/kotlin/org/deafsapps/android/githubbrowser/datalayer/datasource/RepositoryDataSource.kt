package org.deafsapps.android.githubbrowser.datalayer.datasource

import arrow.core.Either
import org.deafsapps.android.githubbrowser.datalayer.domain.dtoToBo
import org.deafsapps.android.githubbrowser.datalayer.service.GithubApiService
import org.deafsapps.android.githubbrowser.datalayer.utils.safeCall
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import retrofit2.Retrofit

interface RepositoryDataSource {

    companion object {
        const val REPOSITORY_DATA_SOURCE_TAG = "repositoryDataSource"
        const val REPOSITORY_DATA_API_SERVICE_TAG = "repositoryDataApiService"
        const val GITHUB_BASE_URL = "https://api.github.com"
    }

    suspend fun fetchDataRepositoriesResponse(): Either<FailureBo, DataRepoBoWrapper>

}

class GithubDataSource(private val retrofitBuilder: Retrofit) : RepositoryDataSource {

    override suspend fun fetchDataRepositoriesResponse(): Either<FailureBo, DataRepoBoWrapper> =
        retrofitBuilder.create(GithubApiService::class.java).fetchPopularGithubReposAsync()
            .safeCall(transform = { it.dtoToBo() })

}