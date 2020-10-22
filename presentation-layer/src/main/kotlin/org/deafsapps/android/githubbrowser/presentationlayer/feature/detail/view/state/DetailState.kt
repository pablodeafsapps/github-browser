package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state

import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo

sealed class DetailState : BaseState() {
    class ShowDataRepoInfo(val dataRepo: DataRepoVo) : DetailState()
    class ShowError(val failure: FailureVo?) : DetailState()
}