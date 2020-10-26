package org.deafsapps.android.githubbrowser.datalayer.service

import org.deafsapps.android.githubbrowser.datalayer.domain.GithubRepoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// https://api.github.com/search/repositories?q=language:kotlin+stars:>=500+sort:stars
interface GithubApiService {

    //    @GET("repositories?q=language:kotlin+stars:>=500+sort:stars")
    @GET
    suspend fun fetchPopularGithubReposAsync(
        @Url url: String = "repositories?q=language:kotlin+stars:>=500+sort:stars"
    ): Response<List<GithubRepoDto>>

}