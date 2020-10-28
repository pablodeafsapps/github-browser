package org.deafsapps.android.githubbrowser.domainlayer.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract.Datalayer.Companion.DATA_REPOSITORY_TAG
import org.deafsapps.android.githubbrowser.domainlayer.R
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import javax.inject.Inject
import javax.inject.Named

const val FETCH_DATA_REPOSITORY_DETAIL_UC_TAG = "fetchDataRepositoryDetailUc"

class FetchDataRepositoryDetailUc @Inject constructor(
    @Named(DATA_REPOSITORY_TAG)
    private val dataRepository: @JvmSuppressWildcards DomainlayerContract.Datalayer.DataRepository<DataRepoBoWrapper>
) : DomainlayerContract.Presentationlayer.UseCase<Long, DataRepoBo> {

    override suspend fun run(params: Long?): Either<FailureBo, DataRepoBo> =
        params?.let { repoId ->
            dataRepository.fetchLocalDataRepositories().flatMap { wrapper ->
                try {
                    wrapper.items.first { it.id == repoId }.right()
                } catch (e: Exception) {
                    FailureBo.NoData(R.string.error_no_data).left()
                }
            }
        } ?: run {
            FailureBo.InputParamsError(R.string.error_params_cannot_be_empty).left()
        }

}