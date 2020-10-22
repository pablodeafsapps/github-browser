package org.deafsapps.android.githubbrowser.datalayer.service

import org.deafsapps.android.githubbrowser.datalayer.domain.GithubRepoDtoWrapper
import retrofit2.Response
import retrofit2.http.POST

// https://api.github.com/search/repositories?q=language:kotlin+stars:>=500+sort:stars
interface GithubApiService {

    @POST("repositories?q=language:kotlin+stars:>=500+sort:stars")
    suspend fun fetchPopularGithubReposAsync(): Response<GithubRepoDtoWrapper>

}