package org.deafsapps.android.githubbrowser.datalayer.datasource

import arrow.core.Either
import retrofit2.Retrofit

interface JokesDataSource {

    companion object {
        const val JOKES_DATA_SOURCE_TAG = "jokesDataSource"
        const val JOKES_API_SERVICE_TAG = "jokesApiService"
        const val ICNDB_BASE_URL = "https://api.github.com"
    }

    suspend fun fetchJokesResponse(): Either<FailureBo, JokeBoWrapper>

}


class IcndbDataSource(private val retrofitBuilder: Retrofit) : JokesDataSource {

    override suspend fun fetchJokesResponse(): Either<FailureBo, JokeBoWrapper> =
        retrofitBuilder.create(IcndbApiService::class.java).getJokesAsync()
            .safeCall(transform = { it.dtoToBo() })

}