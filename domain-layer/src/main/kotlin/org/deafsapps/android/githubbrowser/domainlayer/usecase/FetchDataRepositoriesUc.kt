package org.deafsapps.android.githubbrowser.domainlayer.usecase

import arrow.core.Either
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract.Datalayer.Companion.DATA_REPOSITORY_TAG
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import javax.inject.Inject
import javax.inject.Named

const val FETCH_DATA_REPOSITORIES_UC_TAG = "fetchDataRepositoriesUc"

class FetchDataRepositoriesUc @Inject constructor(
    @Named(DATA_REPOSITORY_TAG)
    private val dataRepository: @JvmSuppressWildcards DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper>
) : DomainlayerContract.Presentationlayer.UseCase<Any, DataRepoBoWrapper> {

    override suspend fun run(params: Any?): Either<FailureBo, DataRepoBoWrapper> = dataRepository.fetchAndCacheDataRepositories()

}