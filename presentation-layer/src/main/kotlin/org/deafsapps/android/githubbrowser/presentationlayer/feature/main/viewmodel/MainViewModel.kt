package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MAIN_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.TimestampVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVoFailure
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState
import org.deafsapps.android.githubbrowser.presentationlayer.utils.getCurrentTimestamp
import javax.inject.Inject
import javax.inject.Named

const val MAIN_VIEW_MODEL_TAG = "mainViewModel"

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    @Named(MAIN_DOMAIN_BRIDGE_TAG)
    bridge: @JvmSuppressWildcards MainDomainLayerBridge<DataRepoBoWrapper>
) : BaseMvvmViewModel<MainDomainLayerBridge<DataRepoBoWrapper>, MainState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        fetchFreshDataRepositories()
    }

    fun onRefreshTriggered() {
        _screenState.value = ScreenState.Loading
        fetchFreshDataRepositories()
    }

    fun onDataRepositorySelected(id: Long) {
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoDetail(id))
    }

    private fun fetchFreshDataRepositories() {
        bridge.fetchDataRepositories(scope = viewModelScope, onResult = {
            it.fold(::handleError, ::handleSuccess)
        })
    }

    private fun handleSuccess(wrapper: DataRepoBoWrapper) {
        val list = listOf(TimestampVo(timestamp = getCurrentTimestamp())) + wrapper.items.boToVo()
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoList(list))
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value = ScreenState.Render(MainState.ShowError(failureBo.boToVoFailure()))
    }

}