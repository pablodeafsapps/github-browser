package org.deafsapps.android.githubbrowser.datalayer.repository

import arrow.core.Either
import org.deafsapps.android.githubbrowser.datalayer.datasource.AuthenticationDataSource
import org.deafsapps.android.githubbrowser.datalayer.domain.FailureDto
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo

object Repository : DomainlayerContract.Datalayer.AuthenticationRepository<UserLoginBo, Boolean>,
    DomainlayerContract.Datalayer.DataRepository<JokeBoWrapper> {

    lateinit var authenticationDataSource: AuthenticationDataSource
    lateinit var jokesDataSource: JokesDataSource

    override fun loginUser(params: UserLoginBo): Either<FailureBo, Boolean> =
        authenticationDataSource.requestLogin(userData = params.boToDto())
            .mapLeft { FailureDto.FirebaseLoginError.dtoToBoFailure() }

    override fun registerUser(params: UserLoginBo): Either<FailureBo, Boolean> =
        authenticationDataSource.requestRegister(userData = params.boToDto())
            .mapLeft { FailureDto.FirebaseRegisterError(msgRes = it.msgRes).dtoToBoFailure() }

    override suspend fun fetchJokes(): Either<FailureBo, JokeBoWrapper> =
        jokesDataSource.fetchJokesResponse()

}