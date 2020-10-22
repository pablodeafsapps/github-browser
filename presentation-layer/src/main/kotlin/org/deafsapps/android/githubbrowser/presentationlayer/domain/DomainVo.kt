package org.deafsapps.android.githubbrowser.presentationlayer.domain

import androidx.annotation.StringRes

data class GithubRepoVoWrapper(
    val totalCount: Int,
    val incomplete_results: Boolean,
    val items: List<DataRepoVo>
)

data class DataRepoVo(
    val id: Long,
    val name: String,
    val owner: OwnerVo,
    val htmlUrl: String,
    val description: String,
    val stars: Int,
    val forks: Int
)

data class OwnerVo(
    val login: String,
    val profilePic: String,
    val type: String
)

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