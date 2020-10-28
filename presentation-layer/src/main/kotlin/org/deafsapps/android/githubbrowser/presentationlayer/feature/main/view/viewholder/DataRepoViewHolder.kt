package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import org.deafsapps.android.githubbrowser.presentationlayer.R
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseViewHolder
import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataView
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataViewAction

class DataRepoViewHolder(itemView: View) : BaseViewHolder<DataView, DataViewAction>(itemView) {

    private val container by lazy { itemView }
    private val tvRepoName: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvRepoName) }
    private val tvRepoDescription: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvRepoDescription) }
    private val ivProfilePic: AppCompatImageView? by lazy { itemView.findViewById(R.id.ivProfilePic) }
    private val tvStars: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvDetailStars) }
    private val tvForks: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvDetailForks) }
    private val tvLanguage: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvDetailLanguage) }

    override fun onBind(item: DataView, callback: (DataViewAction) -> Unit) {
        (item as? DataRepoVo)?.let { dataRepo ->
            with(dataRepo) {
                tvRepoName?.text = HtmlCompat.fromHtml(name, HtmlCompat.FROM_HTML_MODE_COMPACT)
                tvRepoDescription?.text = description
                ivProfilePic?.run { Glide.with(container).load(owner.profilePic).into(this) }
                tvStars?.text = itemView.context.getString(R.string.default_repo_data, stars.toString())
                tvForks?.text = itemView.context.getString(R.string.default_repo_data, forks.toString())
                tvLanguage?.text = itemView.context.getString(R.string.default_repo_data, language)
                container.setOnClickListener { callback(DataViewAction.DataRepoTapped(item = item)) }
            }
        }
    }

}