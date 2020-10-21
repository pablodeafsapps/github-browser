package org.deafsapps.android.githubbrowser.presentationlayer.domain

import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo


private const val DEFAULT_STRING_VALUE = "none"

/**
 * Extension function which maps a list of joke Business Objects to a list of joke Visual Objects
 *
 * @return the list of [JokeVo] type equivalent data
 *
 * @author Pablo L. Sordo
 * @since 1.0
 */
fun List<JokeBo>.boToVo(): List<JokeVo> = map { it.boToVo() }

private fun JokeBo.boToVo(): JokeVo =
    JokeVo(id = id, joke = joke, categories = categories)

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
        is FailureBo.NoData -> FailureVo.NoData(msgRes = msgRes)
        is FailureBo.NoConnection -> FailureVo.NoConnection(msgRes = msgRes)
        is FailureBo.Unknown -> FailureVo.Unknown(msgRes = msgRes)
    }