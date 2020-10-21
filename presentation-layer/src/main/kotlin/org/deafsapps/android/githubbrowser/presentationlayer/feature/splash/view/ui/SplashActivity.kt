package org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView

@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity(),
    BaseMvvmView<SplashActivityViewModel, BaseDomainLayerBridge.None, SplashState> {

    override val viewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.LoadingFinished -> startLoginActivity()
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

    private fun startLoginActivity() {
        startActivity<LoginActivity>()
        finish()
    }

}