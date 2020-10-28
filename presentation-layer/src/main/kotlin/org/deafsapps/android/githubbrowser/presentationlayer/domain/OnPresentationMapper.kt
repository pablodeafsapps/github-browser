package org.deafsapps.android.githubbrowser.presentationlayer.domain

import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.OwnerBo

fun List<DataRepoBo>.boToVo() = map { it.boToVo() }

fun DataRepoBo.boToVo() = DataRepoVo(
    id = id,
    name = name,
    owner = owner.boToVo(),
    htmlUrl = htmlUrl,
    description = description,
    stars = stars,
    forks = forks,
    language = language
)

private fun OwnerBo.boToVo() = OwnerVo(
    login = login,
    profilePic = profilePic,
    type = type
)

/**
 * Extension function which maps a failure Business Object to a failure Visual Object
 *
 * @return the [FailureVo] type equivalent datum
 *
 * @author Pablo L. Sordo
 * @since 1.0
 */
fun FailureBo.boToVoFailure(): FailureVo =
    when (this) {
        is FailureBo.InputParamsError -> FailureVo.Error(msgRes = msgRes)
        is FailureBo.RequestError -> FailureVo.Error(msgRes = msgRes)
        is FailureBo.ServerError -> FailureVo.Error(msgRes = msgRes)
        is FailureBo.NoCachedData -> FailureVo.NoCachedData(msgRes = msgRes)
        is FailureBo.NoData -> FailureVo.NoData(msgRes = msgRes)
        is FailureBo.NoConnection -> FailureVo.NoConnection(msgRes = msgRes)
        is FailureBo.Unknown -> FailureVo.Unknown(msgRes = msgRes)
    }