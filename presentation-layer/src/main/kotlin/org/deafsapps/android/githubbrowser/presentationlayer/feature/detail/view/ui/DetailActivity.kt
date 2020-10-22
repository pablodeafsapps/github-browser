package org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.R
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.databinding.ActivityDetailBinding
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel.DetailViewModel

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity(),
    BaseMvvmView<DetailViewModel, BaseDomainLayerBridge.None, DetailState> {

    private lateinit var _viewModel: DetailViewModel
    override val viewModel: DetailViewModel by lazy { _viewModel }
    private lateinit var viewBinding: ActivityDetailBinding
    private var jokeItem: DataRepoVo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
//        jokeItem = intent.getParcelableExtra(INTENT_DATA_KEY) as? JokeVo
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewCreated(jokeItem)
    }

    override fun processRenderState(renderState: DetailState) {
        when (renderState) {
            is DetailState.ShowDataRepoInfo -> loadJokeItem(renderState.dataRepo)
            is DetailState.ShowError -> showError(renderState.failure)
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Idle -> hideLoading()
                    is ScreenState.Loading -> showLoading()
                    is ScreenState.Render<DetailState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }

    private fun loadJokeItem(item: DataRepoVo) {
        with(viewBinding) {
            tvId.text = getString(R.string.tv_detail_id, item.name)
//            tvJoke.text = HtmlCompat.fromHtml(item.joke ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
//            tvCategories.text = item.categories.takeIf { it?.isNotEmpty() == true }?.toString()
        }
    }

    private fun showLoading() {
        viewBinding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.pbLoading.visibility = View.GONE
    }

    private fun showError(failure: FailureVo?) {
        if (failure?.getErrorMessage() != null) {
            Toast.makeText(this, failure.getErrorMessage(), Toast.LENGTH_SHORT).show()
        }
    }

}