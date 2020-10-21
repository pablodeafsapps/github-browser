package org.deafsapps.android.githubbrowser.presentationlayer.domain

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents the Visual Object related to a joke datum
 *
 * @author Pablo L. Sordo
 * @since 1.0
 */
@Parcelize
data class JokeVo(val id: Int?, val joke: String?, val categories: List<String>?) : CnJokeView.JokeTypeOne(), Parcelable

/**
 * This sealed class contains the 'failure' type definitions to be used in the 'presentation-layer' module
 *
 * @author Pablo L. Sordo
 * @since 1.0
 */
sealed class FailureVo(@StringRes var msgRes: Int?) {

    companion object {
        private const val DEFAULT_STRING_RESOURCE = -1
    }

    fun getErrorMessage(): Int = msgRes ?: DEFAULT_STRING_RESOURCE

    class NoConnection(msgRes: Int) : FailureVo(msgRes = msgRes)
    class Error(msgRes: Int) : FailureVo(msgRes = msgRes)
    class NoData(msgRes: Int?) : FailureVo(msgRes = msgRes)
    class Unknown(msgRes: Int) : FailureVo(msgRes = msgRes)

}