package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVoFailure
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState

@ExperimentalCoroutinesApi
class MainViewModel(bridge: MainDomainLayerBridge<DataRepoBoWrapper>) :
    BaseMvvmViewModel<MainDomainLayerBridge<DataRepoBoWrapper>, MainState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        bridge.fetchDataRepositories(scope = viewModelScope, onResult = {
            it.fold(::handleError, ::handleSuccess)
        })
    }

    fun onDataRepositorySelected(item: DataRepoBo) {
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoDetail(item.boToVo()))
    }

    private fun handleSuccess(wrapper: DataRepoBoWrapper) {
        _screenState.value = ScreenState.Render(MainState.ShowDataRepoList(wrapper.items.boToVo()))
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value = ScreenState.Render(MainState.ShowError(failureBo.boToVoFailure()))
    }

}