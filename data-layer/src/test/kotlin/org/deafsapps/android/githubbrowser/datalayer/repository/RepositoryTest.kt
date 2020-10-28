package org.deafsapps.android.githubbrowser.datalayer.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.deafsapps.android.githubbrowser.datalayer.datasource.ConnectivityDataSource
import org.deafsapps.android.githubbrowser.datalayer.datasource.RepositoryDataSource
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.OwnerBo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

private const val DEFAULT_LONG_VALUE = 0L
private const val DEFAULT_INTEGER_VALUE = 0
private const val DEFAULT_STRING_VALUE = ""
private const val DEFAULT_BOOLEAN_VALUE = false

@ExperimentalCoroutinesApi
class RepositoryTest {

    private lateinit var mockConnectivityDataSource: ConnectivityDataSource
    private lateinit var mockRepositoryDataSource: RepositoryDataSource
    private lateinit var repository: DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper>

    @Before
    fun setUp() {
        mockConnectivityDataSource = mock()
        mockRepositoryDataSource = mock()

        repository = Repository.apply {
            connectivityDataSource = mockConnectivityDataSource
            repositoryDataSource = mockRepositoryDataSource
        }
    }

    @Test
    fun `When there's no connection, a 'NoConnection' error is returned`() = runBlockingTest {
        // given
        whenever(mockConnectivityDataSource.checkNetworkConnectionAvailability()).doReturn(false)
        // when
        val response = repository.fetchAndCacheDataRepositories()
        // then
        Assert.assertTrue(response.isLeft() && (response as Either.Left<FailureBo>).a is FailureBo.NoConnection)
    }

    @Test
    fun `When there's connection, but the data-source fails, a 'NoData' error is returned`() = runBlockingTest {
        // given
        whenever(mockConnectivityDataSource.checkNetworkConnectionAvailability()).doReturn(true)
        whenever(mockRepositoryDataSource.fetchDataRepositoriesResponse()).doReturn(FailureBo.NoData(DEFAULT_INTEGER_VALUE).left())
        // when
        val response = repository.fetchAndCacheDataRepositories()
        // then
        Assert.assertTrue(response.isLeft() && (response as Either.Left<FailureBo>).a is FailureBo.NoData)
    }

    @Test
    fun `When there's connection, but the data-source triggers an exception, an 'Unknown' error is returned`() = runBlockingTest {
        // given
        whenever(mockConnectivityDataSource.checkNetworkConnectionAvailability()).doReturn(true)
        whenever(mockRepositoryDataSource.fetchDataRepositoriesResponse()).doThrow(IllegalStateException())
        // when
        val response = repository.fetchAndCacheDataRepositories()
        // then
        Assert.assertTrue(response.isLeft() && (response as Either.Left<FailureBo>).a is FailureBo.Unknown)
    }

    @Test
    fun `When there's connection, and the data-source succeeds, a list of data is returned`() = runBlockingTest {
        // given
        whenever(mockConnectivityDataSource.checkNetworkConnectionAvailability()).doReturn(true)
        whenever(mockRepositoryDataSource.fetchDataRepositoriesResponse()).doReturn(getDummyDataRepoBoWrapper().right())
        // when
        val response = repository.fetchAndCacheDataRepositories()
        // then
        Assert.assertTrue(response.isRight() && response as? Either.Right<DataRepoBoWrapper> != null)
    }

    @Test
    fun `When data is cached, a 'DataRepoBoWrapper' object is returned`() = runBlockingTest {
        // given
        whenever(mockConnectivityDataSource.checkNetworkConnectionAvailability()).doReturn(true)
        whenever(mockRepositoryDataSource.fetchDataRepositoriesResponse()).doReturn(getDummyDataRepoBoWrapper().right())
        repository.fetchAndCacheDataRepositories()   // caches some dummy data
        // when
        val response = repository.fetchLocalDataRepositories()
        // then
        Assert.assertTrue(response.isRight() && response as? Either.Right<DataRepoBoWrapper> != null)
    }

    @Test
    fun `When data is not cached, a 'NoCachedData' error is returned`() = runBlockingTest {
        // given
        repository.clearDataCache()
        // when
        val response = repository.fetchLocalDataRepositories()
        // then
        Assert.assertTrue(response.isLeft() && (response as? Either.Left)?.a is FailureBo.NoCachedData)
    }

    private fun getDummyDataRepoBoWrapper() = DataRepoBoWrapper(
        totalCount = DEFAULT_INTEGER_VALUE,
        incompleteResults = DEFAULT_BOOLEAN_VALUE,
        items = getDummyDataRepoBoList()
    )

    private fun getDummyDataRepoBoList() = listOf(getDummyDataRepoBo())

    private fun getDummyDataRepoBo() = DataRepoBo(
        id = DEFAULT_LONG_VALUE,
        name = DEFAULT_STRING_VALUE,
        owner = getDummyOwnerBo(),
        htmlUrl = DEFAULT_STRING_VALUE,
        description = DEFAULT_STRING_VALUE,
        stars = DEFAULT_INTEGER_VALUE,
        forks = DEFAULT_INTEGER_VALUE,
        language = DEFAULT_STRING_VALUE
    )

    private fun getDummyOwnerBo() = OwnerBo(
        login = DEFAULT_STRING_VALUE,
        profilePic = DEFAULT_STRING_VALUE,
        type = DEFAULT_STRING_VALUE
    )

}