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
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui.MainActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state.SplashState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel.SplashActivityViewModel

@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity(),
    BaseMvvmView<SplashActivityViewModel, BaseDomainLayerBridge.None, SplashState> {

    private lateinit var _viewModel: SplashActivityViewModel
    override val viewModel: SplashActivityViewModel by lazy { _viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel()
    }

    override fun onResume() {
        super.onResume()
//        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.LoadingFinished -> navigateToMainView()
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
//            viewModel.screenState.collect { screenState ->
//                when (screenState) {
//                    is ScreenState.Render<SplashState> -> processRenderState(screenState.renderState)
//                }
//            }
        }
    }

    private fun navigateToMainView() {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

}