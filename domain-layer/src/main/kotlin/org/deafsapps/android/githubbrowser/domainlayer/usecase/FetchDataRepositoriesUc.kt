package org.deafsapps.android.githubbrowser.domainlayer.usecase

import arrow.core.Either
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper

const val FETCH_DATA_REPOSITORIES_UC_TAG = "fetchDataRepositoriesUc"

class FetchDataRepositoriesUc(private val dataRepository: DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper>) :
    DomainlayerContract.Presentationlayer.UseCase<Any, DataRepoBoWrapper> {

    override suspend fun run(params: Any?): Either<FailureBo, DataRepoBoWrapper> = dataRepository.fetchDataRepositories()

}