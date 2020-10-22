package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state

import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseState
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo

sealed class MainState : BaseState() {
    class ShowDataRepoList(val dataRepoList: List<DataRepoVo>) : MainState()
    class ShowDataRepoDetail(val dataRepo: DataRepoVo) : MainState()
    class ShowError(val failure: FailureVo?) : MainState()
}