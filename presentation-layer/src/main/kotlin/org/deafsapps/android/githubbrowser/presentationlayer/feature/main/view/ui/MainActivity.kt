package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmView
import org.deafsapps.android.githubbrowser.presentationlayer.base.ScreenState
import org.deafsapps.android.githubbrowser.presentationlayer.databinding.ActivityMainBinding
import org.deafsapps.android.githubbrowser.presentationlayer.domain.FailureVo
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.ui.DetailActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel.MainViewModel

const val INTENT_DATA_KEY = "dataRepoItemId"

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(),
    BaseMvvmView<MainViewModel, MainDomainLayerBridge<DataRepoBoWrapper>, MainState> {

    lateinit var _viewModel: MainViewModel
    override val viewModel: MainViewModel by lazy { _viewModel }
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
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
            is MainState.ShowDataRepoDetail -> navigateToDetailView(renderState.dataRepo)
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
        with(viewBinding.rvItems) {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
//            adapter = CnJokeListAdapter(itemList = mutableListOf()) { action ->
//                when (action) {
//                    is CnJokeActionView.JokeItemTapped -> viewModel.onJokeItemSelected(action.item)
//                    CnJokeActionView.JokeItemLongSelected -> longToast("Item long-clicked")
//                }
//            }
        }
    }

    private fun loadReposData(data: List<DataRepoVo>) {
        with(viewBinding) {
            tvNoData.visibility = View.GONE
//            (rvItems.adapter as? CnJokeListAdapter)?.updateData(data)
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

    private fun navigateToDetailView(item: DataRepoVo) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra(INTENT_DATA_KEY, item.id))
    }

    private fun showError(failure: FailureVo?) {
        if (failure?.getErrorMessage() != null) {
            viewBinding.tvNoData.visibility = View.VISIBLE
            Toast.makeText(this, failure.getErrorMessage(), Toast.LENGTH_SHORT).show()
        }
    }

}