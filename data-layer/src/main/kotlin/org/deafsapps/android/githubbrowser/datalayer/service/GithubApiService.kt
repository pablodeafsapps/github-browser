package org.deafsapps.android.githubbrowser.datalayer.service

import retrofit2.Response
import retrofit2.http.POST

// https://api.github.com/search/repositories?q=language:kotlin+stars:>=500+sort:stars
interface GithubApiService {

    @POST("jokes")
    suspend fun getJokesAsync(): Response<JokeDtoWrapper>

}