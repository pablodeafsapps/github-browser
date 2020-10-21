package org.deafsapps.android.githubbrowser.datalayer.domain

import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo

private const val DEFAULT_INTEGER_VALUE = 0
private const val DEFAULT_STRING_VALUE = ""

fun FailureDto.dtoToBoFailure(): FailureBo = when (this) {
    FailureDto.NoConnection -> FailureBo.NoConnection(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    is FailureDto.RequestError -> FailureBo.RequestError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    FailureDto.FirebaseLoginError -> FailureBo.ServerError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    is FailureDto.FirebaseRegisterError -> FailureBo.ServerError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    is FailureDto.Error -> FailureBo.ServerError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    FailureDto.NoData -> FailureBo.NoData(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    FailureDto.Unknown -> FailureBo.Unknown(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
}