package org.deafsapps.android.githubbrowser.datalayer.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import org.deafsapps.android.githubbrowser.datalayer.R
import org.deafsapps.android.githubbrowser.datalayer.datasource.ConnectivityDataSource
import org.deafsapps.android.githubbrowser.datalayer.datasource.RepositoryDataSource
import org.deafsapps.android.githubbrowser.datalayer.domain.FailureDto
import org.deafsapps.android.githubbrowser.datalayer.domain.dtoToBoFailure
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import java.net.SocketTimeoutException

object Repository : DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper> {

    lateinit var connectivityDataSource: ConnectivityDataSource
    lateinit var repositoryDataSource: RepositoryDataSource

    private var dataRepoBoWrapper: Either<FailureBo, DataRepoBoWrapper>? = null

    /**
     * This method fetches a list of repositories from a specific data-source. It
     * first checks whether a connection is available, to later query the service.
     *
     * @return A [List<DataRepoBo>] if successful or a [FailureBo] otherwise
     * @throws SocketTimeoutException
     */
    @Throws(SocketTimeoutException::class)
    override suspend fun fetchAndCacheDataRepositories(): Either<FailureBo, DataRepoBoWrapper> =
        try {
            connectivityDataSource.checkNetworkConnectionAvailability().takeIf { it }?.let {
                repositoryDataSource.fetchDataRepositoriesResponse().apply { dataRepoBoWrapper = this }
            } ?: run {
                FailureDto.NoConnection.dtoToBoFailure().left()
            }
        } catch (e: IllegalStateException) {
            FailureDto.Unknown.dtoToBoFailure().left()
        }

    /**
     * This method fetches a cached list of repositories
     *
     * @return An [Either] if data is cached or a [FailureBo] otherwise
     */
    override suspend fun fetchLocalDataRepositories(): Either<FailureBo, DataRepoBoWrapper> =
        dataRepoBoWrapper ?: FailureBo.NoCachedData(R.string.error_no_cached_data).left()

    override suspend fun clearDataCache(): Either<FailureBo, Boolean> {
        dataRepoBoWrapper = null
        return (dataRepoBoWrapper == null).right()
    }

}