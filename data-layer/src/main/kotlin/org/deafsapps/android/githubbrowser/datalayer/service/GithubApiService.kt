package org.deafsapps.android.githubbrowser.datalayer.service

import org.deafsapps.android.githubbrowser.datalayer.domain.GithubRepoDtoWrapper
import retrofit2.Response
import retrofit2.http.GET

interface GithubApiService {

    @GET("/search/repositories?q=stars:%3E500&sort=stars")
    suspend fun fetchPopularGithubReposAsync(): Response<GithubRepoDtoWrapper>

}