package org.deafsapps.android.githubbrowser.presentationlayer.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<S, T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: S, callback: (T) -> Unit = {})

}