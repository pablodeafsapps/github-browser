package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state.SplashState
import javax.inject.Inject

const val SPLASH_VIEW_MODEL_TAG = "splashViewModel"

@ExperimentalCoroutinesApi
class SplashViewModel @Inject constructor(bridge: BaseDomainLayerBridge.None) :
    BaseMvvmViewModel<BaseDomainLayerBridge.None, SplashState>(bridge = bridge) {

    fun onViewResumed() {
        _screenState.value = ScreenState.Render(SplashState.LoadingFinished)
    }

}