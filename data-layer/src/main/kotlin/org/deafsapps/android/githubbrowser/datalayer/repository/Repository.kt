package org.deafsapps.android.githubbrowser.datalayer.repository

import arrow.core.Either
import org.deafsapps.android.githubbrowser.datalayer.datasource.RepositoryDataSource
import org.deafsapps.android.githubbrowser.datalayer.domain.FailureDto
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo

object Repository : DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper> {

    lateinit var repositoryDataSource: RepositoryDataSource

    override suspend fun fetchDataRepositories(): Either<FailureBo, DataRepoBoWrapper> =
        repositoryDataSource.fetchDataRepositoriesResponse()

}