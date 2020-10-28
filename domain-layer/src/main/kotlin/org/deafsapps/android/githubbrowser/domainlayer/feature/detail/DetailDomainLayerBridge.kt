package org.deafsapps.android.githubbrowser.domainlayer.feature.detail

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FETCH_DATA_REPOSITORY_DETAIL_UC_TAG
import javax.inject.Inject
import javax.inject.Named

const val DETAIL_DOMAIN_BRIDGE_TAG = "detailDomainBridge"

interface DetailDomainLayerBridge<out S> : BaseDomainLayerBridge {

    fun fetchLocalDataRepositories(
        scope: CoroutineScope,
        params: Long?,
        onResult: (Either<FailureBo, S>) -> Unit = {}
    )

}

class DetailDomainLayerBridgeImpl @Inject constructor(
    @Named(FETCH_DATA_REPOSITORY_DETAIL_UC_TAG)
    private val fetchDataRepositoryDetailUc: @JvmSuppressWildcards DomainlayerContract.Presentationlayer.UseCase<Long, DataRepoBo>
) : DetailDomainLayerBridge<DataRepoBo> {

    override fun fetchLocalDataRepositories(
        scope: CoroutineScope, params: Long?, onResult: (Either<FailureBo, DataRepoBo>) -> Unit
    ) {
        fetchDataRepositoryDetailUc.invoke(scope = scope, params = params, onResult = onResult)
    }

}