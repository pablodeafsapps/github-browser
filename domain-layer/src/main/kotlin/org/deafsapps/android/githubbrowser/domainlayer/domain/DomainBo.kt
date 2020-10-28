package org.deafsapps.android.githubbrowser.domainlayer.domain

private const val DEFAULT_STRING_RESOURCE = -1

data class DataRepoBoWrapper(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<DataRepoBo>
)

data class DataRepoBo(
    val id: Long,
    val name: String,
    val owner: OwnerBo,
    val htmlUrl: String,
    val description: String,
    val stars: Int,
    val forks: Int,
    val language: String
)

data class OwnerBo(
    val login: String,
    val profilePic: String,
    val type: String
)

sealed class FailureBo(var msgRes: Int = DEFAULT_STRING_RESOURCE) {
    class NoConnection(msgRes: Int): FailureBo(msgRes = msgRes)
    class InputParamsError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class RequestError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class NoCachedData(msgRes: Int) : FailureBo(msgRes = msgRes)
    class ServerError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class NoData(msgRes: Int) : FailureBo(msgRes = msgRes)
    class Unknown(msgRes: Int) : FailureBo(msgRes = msgRes)
}