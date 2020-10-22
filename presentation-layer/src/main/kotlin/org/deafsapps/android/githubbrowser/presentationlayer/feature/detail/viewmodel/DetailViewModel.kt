package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.R
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState

@ExperimentalCoroutinesApi
class DetailViewModel(bridge: BaseDomainLayerBridge.None) :
    BaseMvvmViewModel<BaseDomainLayerBridge.None, DetailState>(bridge = bridge) {

    fun onViewCreated(dataRepoItem: DataRepoVo?) {
        _screenState.value = ScreenState.Render(
            if (dataRepoItem != null) DetailState.ShowDataRepoInfo(dataRepo = dataRepoItem)
            else DetailState.ShowError(FailureVo.NoData(msgRes = R.string.error_no_data))
        )
    }

}