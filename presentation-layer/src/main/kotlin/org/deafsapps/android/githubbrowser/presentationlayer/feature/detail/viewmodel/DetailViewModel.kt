package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.feature.detail.DETAIL_DOMAIN_BRIDGE_TAG
import org.deafsapps.android.githubbrowser.domainlayer.feature.detail.DetailDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.boToVoFailure
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState
import javax.inject.Inject
import javax.inject.Named

const val DETAIL_VIEW_MODEL_TAG = "detailViewModel"

@ExperimentalCoroutinesApi
class DetailViewModel @Inject constructor(
    @Named(DETAIL_DOMAIN_BRIDGE_TAG)
    bridge: DetailDomainLayerBridge<DataRepoBo>
) : BaseMvvmViewModel<DetailDomainLayerBridge<DataRepoBo>, DetailState>(bridge = bridge) {

    fun onViewCreated(dataRepoItem: Long?) {
        _screenState.value = ScreenState.Loading
        bridge.fetchLocalDataRepositories(
            scope = viewModelScope,
            params = dataRepoItem,
            onResult = {
                it.fold(::handleError, ::handleSuccess)
            })
    }

    private fun handleSuccess(repoItem: DataRepoBo) {
        _screenState.value = ScreenState.Render(DetailState.ShowDataRepoInfo(dataRepo = repoItem))
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value = ScreenState.Render(DetailState.ShowError(failureBo.boToVoFailure()))
    }

}