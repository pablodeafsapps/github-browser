package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.deafsapps.android.githubbrowser.presentationlayer.R
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseViewHolder
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.viewholder.DataRepoViewHolder
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.viewholder.UpdateTimestampViewHolder
import java.lang.IllegalStateException

class DataRepoListAdapter(
    private var itemList: List<DataView>, private val onItemSelected: (DataViewAction) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<DataView, DataViewAction>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder<DataView, DataViewAction> =
        when (viewType) {
            DataView.DataViewType.TYPE_DATE.type -> UpdateTimestampViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.update_timestamp, parent, false)
            )
            DataView.DataViewType.TYPE_REPO.type -> DataRepoViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_repo, parent, false)
            )
            else -> throw IllegalStateException("No 'ViewType' defined for this particular case")
        }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = itemList[position].viewType

    override fun onBindViewHolder(holder: BaseViewHolder<DataView, DataViewAction>, position: Int) {
        holder.onBind(
            item = itemList[position],
            callback = { item -> onItemSelected.invoke(item) }
        )
    }

    fun updateData(newData: List<DataView>) {
        itemList = newData
        notifyDataSetChanged()
    }

}