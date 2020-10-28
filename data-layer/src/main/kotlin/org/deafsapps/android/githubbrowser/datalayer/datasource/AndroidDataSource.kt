package org.deafsapps.android.githubbrowser.datalayer.datasource

import android.content.Context
import org.deafsapps.android.githubbrowser.datalayer.utils.isNetworkAvailable
import javax.inject.Inject

interface ConnectivityDataSource {

    companion object {
        const val CONNECTIVITY_DATA_SOURCE_TAG = "connectivityDataSource"
    }

    suspend fun checkNetworkConnectionAvailability(): Boolean

}

class AndroidDataSource @Inject constructor(private val context: Context) : ConnectivityDataSource {

    override suspend fun checkNetworkConnectionAvailability(): Boolean =
        context.isNetworkAvailable()

}