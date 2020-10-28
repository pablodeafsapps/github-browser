package org.deafsapps.android.githubbrowser.datalayer.domain

import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.OwnerBo

private const val DEFAULT_INTEGER_VALUE = -1
private const val DEFAULT_BOOLEAN_VALUE = false
private const val DEFAULT_LONG_VALUE = -1L
private const val DEFAULT_STRING_VALUE = "n/a"

fun GithubRepoDtoWrapper.githubToDataRepoWrapperBo() = DataRepoBoWrapper(
    totalCount = totalCount ?: DEFAULT_INTEGER_VALUE,
    incompleteResults = incompleteResults ?: DEFAULT_BOOLEAN_VALUE,
    items =  items.dataRepoListToBo()
)

fun List<GithubRepoDto>.dataRepoListToBo() = map { it.dtoToBo() }

private fun GithubRepoDto.dtoToBo() = DataRepoBo(
    id = id ?: DEFAULT_LONG_VALUE,
    name = name ?: DEFAULT_STRING_VALUE,
    owner = owner?.dtoToBo() ?: getDummyOwnerBo(),
    htmlUrl = htmlUrl ?: DEFAULT_STRING_VALUE,
    description = description ?: DEFAULT_STRING_VALUE,
    stars = stars ?: DEFAULT_INTEGER_VALUE,
    forks = forks ?: DEFAULT_INTEGER_VALUE,
    language = language ?: DEFAULT_STRING_VALUE
)

private fun getDummyOwnerBo() = OwnerBo(
    login = DEFAULT_STRING_VALUE,
    profilePic = DEFAULT_STRING_VALUE,
    type = DEFAULT_STRING_VALUE
)

private fun OwnerDto.dtoToBo() = OwnerBo(
    login = login ?: DEFAULT_STRING_VALUE,
    profilePic = profilePic ?: DEFAULT_STRING_VALUE,
    type = type ?: DEFAULT_STRING_VALUE
)

fun FailureDto.dtoToBoFailure(): FailureBo = when (this) {
    FailureDto.NoConnection -> FailureBo.NoConnection(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    is FailureDto.RequestError -> FailureBo.RequestError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    is FailureDto.Error -> FailureBo.ServerError(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    FailureDto.NoData -> FailureBo.NoData(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
    FailureDto.Unknown -> FailureBo.Unknown(msgRes = msgRes ?: DEFAULT_INTEGER_VALUE)
}