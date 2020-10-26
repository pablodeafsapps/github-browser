package org.deafsapps.android.githubbrowser.domainlayer.usecase

import arrow.core.Either
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract.Datalayer.Companion.DATA_REPOSITORY_TAG
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import javax.inject.Inject
import javax.inject.Named

const val FETCH_DATA_REPOSITORIES_UC_TAG = "fetchDataRepositoriesUc"

class FetchDataRepositoriesUc @Inject constructor(
    @Named(DATA_REPOSITORY_TAG)
    private val dataRepository: @JvmSuppressWildcards DomainlayerContract.Datalayer.DataRepository<List<DataRepoBo>>
) : DomainlayerContract.Presentationlayer.UseCase<Any, List<DataRepoBo>> {

    override suspend fun run(params: Any?): Either<FailureBo, List<DataRepoBo>> = dataRepository.fetchDataRepositories()

}