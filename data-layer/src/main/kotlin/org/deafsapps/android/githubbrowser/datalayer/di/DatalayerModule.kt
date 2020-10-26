package org.deafsapps.android.githubbrowser.datalayer.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.deafsapps.android.githubbrowser.datalayer.BuildConfig
import org.deafsapps.android.githubbrowser.datalayer.datasource.GithubDataSource
import org.deafsapps.android.githubbrowser.datalayer.datasource.RepositoryDataSource
import org.deafsapps.android.githubbrowser.datalayer.datasource.RepositoryDataSource.Companion.REPOSITORY_DATA_SOURCE_TAG
import org.deafsapps.android.githubbrowser.datalayer.repository.Repository
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract
import org.deafsapps.android.githubbrowser.domainlayer.DomainlayerContract.Datalayer.Companion.DATA_REPOSITORY_TAG
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBoWrapper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val TEN = 10L

@Module
object RepositoryModule {

    @Provides
    @Named(DATA_REPOSITORY_TAG)
    fun provideDataRepository(
        @Named(REPOSITORY_DATA_SOURCE_TAG)
        repositoryDs: RepositoryDataSource
    ): @JvmSuppressWildcards DomainlayerContract.Datalayer.DataRepository<List<DataRepoBo>> =
        Repository.apply { repositoryDataSource = repositoryDs }

}

@Module
class DatasourceModule {

    @Provides
    @Named(REPOSITORY_DATA_SOURCE_TAG)
    fun provideRepositoryDataSource(ds: GithubDataSource): RepositoryDataSource = ds

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .client(getHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(RepositoryDataSource.API_BASE_URL)
            .build()
    }

}

fun getHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(TEN, TimeUnit.SECONDS)
        .readTimeout(TEN, TimeUnit.SECONDS)
        .build()
}