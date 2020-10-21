package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel

@ExperimentalCoroutinesApi
class SplashActivityViewModel(bridge: BaseDomainLayerBridge.None) :
    BaseMvvmViewModel<BaseDomainLayerBridge.None, SplashState>(bridge = bridge) {


    fun onViewCreated() {
        _screenState.value = ScreenState.Render(SplashState.LoadingFinished)
    }

}