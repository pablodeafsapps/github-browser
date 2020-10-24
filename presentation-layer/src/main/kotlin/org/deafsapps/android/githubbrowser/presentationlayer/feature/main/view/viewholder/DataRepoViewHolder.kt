package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.data_repo.view.*
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseViewHolder
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataView
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataViewAction

private const val EMPTY_STRING = ""

class DataRepoViewHolder(itemView: View) : BaseViewHolder<DataView, DataViewAction>(itemView) {

    private val container by lazy { itemView }
    private val tvRepoName: TextView? by lazy { itemView.tvRepoName }
    private val tvRepoDescription: TextView? by lazy { itemView.tvRepoDescription }

    override fun onBind(item: DataView, callback: (DataViewAction) -> Unit) {
        (item as? DataRepoVo)?.let { dataRepo ->
            tvRepoName?.text = HtmlCompat.fromHtml(dataRepo.name, HtmlCompat.FROM_HTML_MODE_COMPACT)
            tvRepoDescription?.text = dataRepo.description
            container.setOnClickListener { callback(DataViewAction.DataRepoTapped(item = item)) }
        }
    }

}