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
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.databinding.ActivityDetailBinding
import org.deafsapps.android.githubbrowser.presentationlayer.di.DetailComponent
import org.deafsapps.android.githubbrowser.presentationlayer.di.DetailComponentFactoryProvider
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel.DETAIL_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel.DetailViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui.INTENT_DATA_KEY
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity(),
    BaseMvvmView<DetailViewModel, BaseDomainLayerBridge.None, DetailState> {

    @Inject
    @Named(DETAIL_VIEW_MODEL_TAG)
    lateinit var _viewModel: BaseMvvmViewModel<BaseDomainLayerBridge.None, DetailState>
    override val viewModel: DetailViewModel by lazy { _viewModel as DetailViewModel }
    private lateinit var viewBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getDetailComponent().inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
//        jokeItem = intent.getParcelableExtra(INTENT_DATA_KEY) as? JokeVo
    }

    override fun onResume() {
        super.onResume()
        val dataRepoId = intent.getLongExtra(INTENT_DATA_KEY, -1L)
        viewModel.onViewCreated(dataRepoId)
    }

    override fun processRenderState(renderState: DetailState) {
        when (renderState) {
            is DetailState.ShowDataRepoInfo -> loadDataRepo(renderState.dataRepo)
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

    private fun loadDataRepo(item: Long) {
        with(viewBinding) {
//            tvId.text = getString(R.string.tv_detail_id, item.name)
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

@ExperimentalCoroutinesApi
private fun DetailActivity.getDetailComponent(): DetailComponent =
    (application as DetailComponentFactoryProvider).provideDetailComponentFactory().create()