package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MAIN_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVoFailure
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState
import javax.inject.Inject
import javax.inject.Named

const val MAIN_VIEW_MODEL_TAG = "mainViewModel"

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    @Named(MAIN_DOMAIN_BRIDGE_TAG)
    bridge:  @JvmSuppressWildcards MainDomainLayerBridge<List<DataRepoBo>>
) : BaseMvvmViewModel<MainDomainLayerBridge<List<DataRepoBo>>, MainState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        bridge.fetchDataRepositories(scope = viewModelScope, onResult = {
            it.fold(::handleError, ::handleSuccess)
        })
    }

    fun onDataRepositorySelected(id: Long) {
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoDetail(id))
    }

    private fun handleSuccess(items: List<DataRepoBo>) {
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoList(items.boToVo()))
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value = ScreenState.Render(MainState.ShowError(failureBo.boToVoFailure()))
    }

}