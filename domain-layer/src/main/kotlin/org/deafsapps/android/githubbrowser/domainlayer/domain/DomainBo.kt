package org.deafsapps.android.githubbrowser.domainlayer.domain

private const val DEFAULT_STRING_RESOURCE = -1

data class UserLoginBo(val email: String, val password: String)

data class JokeBoWrapper(val type: String, val value: List<JokeBo>)

data class JokeBo(val id: Int, val joke: String, val categories: List<String>)

sealed class FailureBo(var msgRes: Int = DEFAULT_STRING_RESOURCE) {
    class NoConnection(msgRes: Int): FailureBo(msgRes = msgRes)
    class InputParamsError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class RequestError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class ServerError(msgRes: Int) : FailureBo(msgRes = msgRes)
    class NoData(msgRes: Int) : FailureBo(msgRes = msgRes)
    class Unknown(msgRes: Int) : FailureBo(msgRes = msgRes)
}