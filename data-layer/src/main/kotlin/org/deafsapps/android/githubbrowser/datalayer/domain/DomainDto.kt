package org.deafsapps.android.githubbrowser.datalayer.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.ResponseBody
import org.deafsapps.android.githubbrowser.domainlayer.R

@JsonClass(generateAdapter = true)
data class GithubRepoDtoWrapper(
    @Json(name = "totalCount") val totalCount: Int?,
    @Json(name = "incomplete_results") val incompleteResults: Boolean?,
    @Json(name = "items") val items: List<GithubRepoDto>
)

@JsonClass(generateAdapter = true)
data class GithubRepoDto(
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val name: String?,
    @Json(name = "owner") val owner: OwnerDto?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "stargazers_count") val stars: Int?,
    @Json(name = "forks_count") val forks: Int?,
    @Json(name = "language") val language: String?
)

@JsonClass(generateAdapter = true)
data class OwnerDto(
    @Json(name = "login") val login: String?,
    @Json(name = "avatar_url") val profilePic: String?,
    @Json(name = "type") val type: String?
)

sealed class FailureDto(val msgRes: Int?) {

    companion object {
        private const val DEFAULT_ERROR_CODE = -1
    }

    object NoConnection : FailureDto(msgRes = R.string.error_no_connection)
    class RequestError(val code: Int = DEFAULT_ERROR_CODE, msgRes: Int?, val errorBody: ResponseBody? = null) : FailureDto(msgRes = msgRes)
    class Error(msgRes: Int?) : FailureDto(msgRes = msgRes)
    object NoData : FailureDto(msgRes = R.string.error_no_data)
    object Unknown : FailureDto(msgRes = R.string.error_unknown)

}