package org.deafsapps.android.githubbrowser.domainlayer.feature.main

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo

const val MAIN_DOMAIN_BRIDGE_TAG = "mainDomainLayerBridge"

interface MainDomainLayerBridge<out S> : BaseDomainLayerBridge {

    fun fetchDataRepositories(scope: CoroutineScope, onResult: (Either<FailureBo, S>) -> Unit = {})

}

internal class MainDomainLayerBridgeImpl(private val fetchJokesUc: DomainlayerContract.Presentationlayer.UseCase<Any, String>) :
    MainDomainLayerBridge<String> {

    override fun fetchDataRepositories(
        scope: CoroutineScope, onResult: (Either<FailureBo, String>) -> Unit
    ) {
        fetchJokesUc.invoke(scope = scope, onResult = onResult)
    }

}