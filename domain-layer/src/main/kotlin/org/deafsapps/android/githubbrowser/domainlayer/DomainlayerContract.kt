package org.deafsapps.android.githubbrowser.domainlayer

import arrow.core.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.deafsapps.android.githubbrowser.domainlayer.domain.FailureBo

interface DomainlayerContract {

    interface Presentationlayer {

        interface UseCase<in T, out S> {
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<FailureBo, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                // task undertaken in a dispatcher worker and once completed, result sent in the scope thread
                scope.launch { onResult(withContext(dispatcherWorker) { run(params) }) }
            }

            suspend fun run(params: T? = null): Either<FailureBo, S>
        }

    }

    interface Datalayer {

        companion object {
            const val DATA_REPOSITORY_TAG = "dataRepository"
        }

        interface DataRepository<out T> {
            suspend fun fetchAndCacheDataRepositories(): Either<FailureBo, T>
            suspend fun fetchLocalDataRepositories(): Either<FailureBo, T>
            suspend fun clearDataCache(): Either<FailureBo, Boolean>
        }

    }

}