package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import org.deafsapps.android.githubbrowser.presentationlayer.R
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseViewHolder
import org.deafsapps.android.githubbrowser.presentationlayer.domain.TimestampVo
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataView
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter.DataViewAction

class UpdateTimestampViewHolder(itemView: View) : BaseViewHolder<DataView, DataViewAction>(itemView) {

    private val tvUpdateTimestamp: AppCompatTextView? by lazy { itemView.findViewById(R.id.tvTimestamp) }

    override fun onBind(item: DataView, callback: (DataViewAction) -> Unit) {
        (item as? TimestampVo)?.let { data ->
            tvUpdateTimestamp?.text = data.timestamp
        }
    }

}