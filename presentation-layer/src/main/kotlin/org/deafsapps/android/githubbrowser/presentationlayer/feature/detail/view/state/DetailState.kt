package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state

sealed class DetailState : BaseState() {
    class ShowJokeInfo(val joke: JokeVo) : DetailState()
    class ShowError(val failure: FailureVo?) : DetailState()
}