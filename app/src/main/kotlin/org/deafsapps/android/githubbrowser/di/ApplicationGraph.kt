package org.deafsapps.android.githubbrowser.di

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.datalayer.di.DatasourceModule
import org.deafsapps.android.githubbrowser.datalayer.di.RepositoryModule
import org.deafsapps.android.githubbrowser.domainlayer.di.BridgeModule
import org.deafsapps.android.githubbrowser.domainlayer.di.UsecaseModule
import org.deafsapps.android.githubbrowser.presentationlayer.di.*

@ExperimentalCoroutinesApi
@ApplicationScope
@Component(
    modules = [UtilsModule::class, PresentationlayerModule::class, BridgeModule::class,
        UsecaseModule::class, RepositoryModule::class, DatasourceModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(modules: UtilsModule): ApplicationComponent
    }

    // downstream dependent components need data types to be exposed
    // 'subcomponents' do not need this exposure! :) (i.e. 'Context' is automatically reachable!)
    fun splashComponentFactory(): SplashComponent.Factory
    fun mainComponentFactory(): MainComponent.Factory
    fun detailComponentFactory(): DetailComponent.Factory

}

@Module
class UtilsModule(private val ctx: Context) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = ctx

}