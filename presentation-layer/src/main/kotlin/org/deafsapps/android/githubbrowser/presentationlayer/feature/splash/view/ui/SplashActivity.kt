package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.di.SplashComponent
import org.deafsapps.android.githubbrowser.presentationlayer.di.SplashComponentFactoryProvider
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui.MainActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state.SplashState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel.SPLASH_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel.SplashViewModel
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity(),
    BaseMvvmView<BaseMvvmViewModel<BaseDomainLayerBridge.None, SplashState>, BaseDomainLayerBridge.None, SplashState> {

    @Inject
    @Named(SPLASH_VIEW_MODEL_TAG)
    lateinit var _viewModel: BaseMvvmViewModel<BaseDomainLayerBridge.None, SplashState>
    override val viewModel: SplashViewModel by lazy { _viewModel as SplashViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        getSplashComponent().inject(this)
        super.onCreate(savedInstanceState)
        initModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewResumed()
    }

    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.LoadingFinished -> navigateToMainView()
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<SplashState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }

    private fun navigateToMainView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}

@ExperimentalCoroutinesApi
private fun SplashActivity.getSplashComponent(): SplashComponent =
    (application as SplashComponentFactoryProvider).provideSplashComponentFactory().create()