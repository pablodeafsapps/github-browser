package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.databinding.ActivityMainBinding
import org.deafsapps.android.githubbrowser.presentationlayer.di.MainComponent
import org.deafsapps.android.githubbrowser.presentationlayer.di.MainComponentFactoryProvider
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.ui.DetailActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataRepoListAdapter
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataView
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataViewAction
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel.MAIN_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel.MainViewModel
import javax.inject.Inject
import javax.inject.Named

const val INTENT_DATA_KEY = "dataRepoItemId"

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(),
    BaseMvvmView<MainViewModel, MainDomainLayerBridge<DataRepoBoWrapper>, MainState> {

    @Inject
    @Named(MAIN_VIEW_MODEL_TAG)
    lateinit var _viewModel: BaseMvvmViewModel<MainDomainLayerBridge<DataRepoBoWrapper>, MainState>
    override val viewModel: MainViewModel by lazy { _viewModel as MainViewModel }
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainComponent().inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        initModel()
        initView()
        setContentView(viewBinding.root)
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: MainState) {
        when (renderState) {
            is MainState.ShowDataRepoList -> loadReposData(renderState.dataRepoList)
            is MainState.ShowDataRepoDetail -> navigateToDetailView(renderState.dataRepoId)
            is MainState.ShowError -> showError(renderState.failure)
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Idle -> hideLoading()
                    is ScreenState.Loading -> showLoading()
                    is ScreenState.Render<MainState> -> {
                        processRenderState(screenState.renderState)
                        hideLoading()
                    }
                }
            }
        }
    }

    private fun initView() {
        with(viewBinding) {
            srLayout.apply {
                setOnRefreshListener {
                    viewModel.onRefreshTriggered()
                    this@apply.isRefreshing = false
                }
            }
            rvItems.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                adapter = DataRepoListAdapter(itemList = mutableListOf()) { action ->
                    when (action) {
                        is DataViewAction.DataRepoTapped -> viewModel.onDataRepositorySelected(action.item.id)
                    }
                }
            }
        }
    }

    private fun loadReposData(data: List<DataView>) {
        with(viewBinding) {
            tvNoData.visibility = View.GONE
            (rvItems.adapter as? DataRepoListAdapter)?.updateData(data)
        }
    }

    private fun showLoading() {
        with(viewBinding) {
            pbLoading.visibility = View.VISIBLE
            rvItems.isEnabled = false
        }
    }

    private fun hideLoading() {
        with(viewBinding) {
            pbLoading.visibility = View.GONE
            rvItems.isEnabled = true
        }
    }

    private fun navigateToDetailView(id: Long) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra(INTENT_DATA_KEY, id))
    }

    private fun showError(failure: FailureVo?) {
        if (failure?.getErrorMessage() != null) {
            viewBinding.tvNoData.visibility = View.VISIBLE
            Toast.makeText(this, failure.getErrorMessage(), Toast.LENGTH_SHORT).show()
        }
    }

}

@ExperimentalCoroutinesApi
private fun MainActivity.getMainComponent(): MainComponent =
    (application as MainComponentFactoryProvider).provideMainComponentFactory().create()