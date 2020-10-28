package org.deafsapps.android.githubbrowser.domainlayer.usecase

import arrow.core.Either
import arrow.core.right
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.OwnerBo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private const val DEFAULT_LONG_VALUE = 0L
private const val DEFAULT_INTEGER_VALUE = 0
private const val DEFAULT_STRING_VALUE = ""
private const val DEFAULT_BOOLEAN_VALUE = false

@ExperimentalCoroutinesApi
class FetchDataRepositoryDetailUcTest {

    private lateinit var fetchDataRepositoryDetailUc: DomainlayerContract.Presentationlayer.UseCase<Long, DataRepoBo>
    private lateinit var mockRepository: DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper>

    @Before
    fun setUp() {
        mockRepository = mock()

        fetchDataRepositoryDetailUc = FetchDataRepositoryDetailUc(dataRepository = mockRepository)
    }

    @Test
    fun `check that if the input parameters are null, an 'InputParamsError' error is returned`() =
        runBlockingTest {
            // given
            val nullParams: Long? = null
            // when
            val response = fetchDataRepositoryDetailUc.run(params = nullParams)
            // then
            Assert.assertTrue(response.isLeft() && (response as Either.Left<FailureBo>).a is FailureBo.InputParamsError)
        }

    @Test
    fun `check that if params are wrong, a 'NoData' error is returned`() = runBlockingTest {
        // given
        val wrongParams = -1L
        whenever(mockRepository.fetchLocalDataRepositories()).doReturn(getDummyDataRepoBoWrapper().right())
        // when
        val response = fetchDataRepositoryDetailUc.run(params = wrongParams)
        // then
        Assert.assertTrue(response.isLeft() && (response as Either.Left<FailureBo>).a is FailureBo.NoData)
    }

    @Test
    fun `check that if params are right, a 'DataResponseBo' is returned`() = runBlockingTest {
        // given
        val rightParams = DEFAULT_LONG_VALUE
        whenever(mockRepository.fetchLocalDataRepositories()).doReturn(getDummyDataRepoBoWrapper().right())
        // when
        val response = fetchDataRepositoryDetailUc.run(params = rightParams)
        // then
        Assert.assertTrue(response.isRight() && response as? Either.Right<DataRepoBo> != null)
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