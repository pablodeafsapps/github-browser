package org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.adapter

import org.deafsapps.android.githubbrowser.presentationlayer.domain.DataRepoVo

sealed class DataView(val viewType: Int)  {

    enum class DataViewType(val type: Int) {
        TYPE_DATE(type = 1), TYPE_REPO(type = 2)
    }

    open class UpdateTimestamp : DataView(viewType = DataViewType.TYPE_DATE.type)
    open class DataRepo : DataView(viewType = DataViewType.TYPE_REPO.type)

}

sealed class DataViewAction {
    data class DataRepoTapped(val item: DataRepoVo) : DataViewAction()
    object DataRepoLongSelected : DataViewAction()
}