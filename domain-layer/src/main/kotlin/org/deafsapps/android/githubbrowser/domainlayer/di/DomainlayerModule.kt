package org.deafsapps.android.githubbrowser.domainlayer.di

import dagger.Module
import dagger.Provides
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.feature.detail.DETAIL_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.detail.DetailDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.feature.detail.DetailDomainLayerBridgeImpl
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MAIN_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridgeImpl
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FETCH_DATA_REPOSITORIES_UC_TAG
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FETCH_DATA_REPOSITORY_DETAIL_UC_TAG
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FetchDataRepositoriesUc
import org.deafsapps.android.githubbrowser.domainlayer.usecase.FetchDataRepositoryDetailUc
import javax.inject.Named

@Module
object BridgeModule {

    @Provides
    fun provideNoDomainBridge() = BaseDomainLayerBridge.None

    @Provides
    @Named(MAIN_DOMAIN_BRIDGE_TAG)
    fun provideMainDomainBridge(bridge: MainDomainLayerBridgeImpl): @JvmSuppressWildcards MainDomainLayerBridge<DataRepoBoWrapper> =
        bridge

    @Provides
    @Named(DETAIL_DOMAIN_BRIDGE_TAG)
    fun provideDetailDomainBridge(bridge: DetailDomainLayerBridgeImpl): @JvmSuppressWildcards DetailDomainLayerBridge<DataRepoBo> =
        bridge

}

@Module
object UsecaseModule {

    @Provides
    @Named(FETCH_DATA_REPOSITORIES_UC_TAG)
    fun provideFetchDataRepositoriesUc(usecase: FetchDataRepositoriesUc): @JvmSuppressWildcards DomainlayerContract.Presentationlayer.UseCase<Any, DataRepoBoWrapper> =
        usecase

    @Provides
    @Named(FETCH_DATA_REPOSITORY_DETAIL_UC_TAG)
    fun providefetchDataRepositoryDetailUc(usecase: FetchDataRepositoryDetailUc): @JvmSuppressWildcards DomainlayerContract.Presentationlayer.UseCase<Long, DataRepoBo> =
        usecase

}