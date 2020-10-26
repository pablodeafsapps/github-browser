package org.deafsapps.android.githubbrowser.domainlayer.feature.main

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FETCH_DATA_REPOSITORIES_UC_TAG
import javax.inject.Inject
import javax.inject.Named

const val MAIN_DOMAIN_BRIDGE_TAG = "mainDomainBridge"

interface MainDomainLayerBridge<out S> : BaseDomainLayerBridge {

    fun fetchDataRepositories(scope: CoroutineScope, onResult: (Either<FailureBo, S>) -> Unit = {})

}

class MainDomainLayerBridgeImpl @Inject constructor(
    @Named(FETCH_DATA_REPOSITORIES_UC_TAG)
    private val fetchDataRepositoriesUc: @JvmSuppressWildcards DomainlayerContract.Presentationlayer.UseCase<Any, DataRepoBoWrapper>
) : MainDomainLayerBridge<DataRepoBoWrapper> {

    override fun fetchDataRepositories(
        scope: CoroutineScope, onResult: (Either<FailureBo, DataRepoBoWrapper>) -> Unit
    ) {
        fetchDataRepositoriesUc.invoke(scope = scope, onResult = onResult)
    }

}