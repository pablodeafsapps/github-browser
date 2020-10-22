package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state

import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseState

sealed class SplashState : BaseState() {
    object LoadingFinished : SplashState()
}