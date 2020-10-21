package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state

sealed class SplashState : BaseState() {
    object LoadingFinished : SplashState()
}