package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state

import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataView

sealed class MainState : BaseState() {
    class ShowDataRepoList(val dataRepoList: List<DataView>) : MainState()
    class ShowDataRepoDetail(val dataRepoId: Long) : MainState()
    class ShowError(val failure: FailureVo?) : MainState()
}