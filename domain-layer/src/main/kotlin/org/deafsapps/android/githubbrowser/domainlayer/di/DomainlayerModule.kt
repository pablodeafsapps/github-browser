package org.deafsapps.android.githubbrowser.domainlayer.di

import dagger.Module
import dagger.Provides
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MAIN_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridgeImpl
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FETCH_DATA_REPOSITORIES_UC_TAG
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FetchDataRepositoriesUc
import javax.inject.Named

@Module
object BridgeModule {

    @Provides
    fun provideNoDomainBridge() = BaseDomainLayerBridge.None

    @Provides
    @Named(MAIN_DOMAIN_BRIDGE_TAG)
    fun provideMainDomainBridge(bridge: MainDomainLayerBridgeImpl): @JvmSuppressWildcards MainDomainLayerBridge<List<DataRepoBo>> =
        bridge

}

@Module
object UsecaseModule {

    @Provides
    @Named(FETCH_DATA_REPOSITORIES_UC_TAG)
    fun provideFetchDataRepositoryUc(usecase: FetchDataRepositoriesUc): @JvmSuppressWildcards DomainlayerContract.Presentationlayer.UseCase<Any, List<DataRepoBo>> =
        usecase

}