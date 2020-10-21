package org.deafsapps.android.githubbrowser.datalayer.domain

import okhttp3.ResponseBody
import org.deafsapps.android.githubbrowser.domainlayer.R

sealed class FailureDto(@StringRes val msgRes: Int?) {

    companion object {
        private const val DEFAULT_ERROR_CODE = -1
    }

    object NoConnection : FailureDto(msgRes = R.string.error_no_connection)
    class RequestError(val code: Int = DEFAULT_ERROR_CODE, msgRes: Int?, val errorBody: ResponseBody? = null) : FailureDto(msgRes = msgRes)
    object FirebaseLoginError : FailureDto(msgRes = R.string.error_login_request)
    class FirebaseRegisterError(msgRes: Int?) : FailureDto(msgRes = msgRes)
    class Error(msgRes: Int?) : FailureDto(msgRes = msgRes)
    object NoData : FailureDto(msgRes = R.string.error_no_data)
    object Unknown : FailureDto(msgRes = R.string.error_unknown)

}