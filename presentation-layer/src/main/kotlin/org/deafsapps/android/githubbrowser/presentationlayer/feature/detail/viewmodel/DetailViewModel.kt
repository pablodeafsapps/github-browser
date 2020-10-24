package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.R
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState
import javax.inject.Inject

const val DETAIL_VIEW_MODEL_TAG = "detailViewModel"

@ExperimentalCoroutinesApi
class DetailViewModel @Inject constructor(bridge: BaseDomainLayerBridge.None) :
    BaseMvvmViewModel<BaseDomainLayerBridge.None, DetailState>(bridge = bridge) {

    fun onViewCreated(dataRepoItem: Long?) {
        _screenState.value = ScreenState.Render(
            if (dataRepoItem != null) DetailState.ShowDataRepoInfo(dataRepo = dataRepoItem)
            else DetailState.ShowError(FailureVo.NoData(msgRes = R.string.error_no_data))
        )
    }

}