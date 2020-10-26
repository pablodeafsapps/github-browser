package org.deafsapps.android.githubbrowser.presentationlayer.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.domainlayer.base.BaseDomainLayerBridge
import org.deafsapps.android.githubbrowser.domainlayer.domain.DataRepoBo
import org.deafsapps.android.githubbrowser.domainlayer.feature.main.MainDomainLayerBridge
import org.deafsapps.android.githubbrowser.presentationlayer.base.BaseMvvmViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.state.DetailState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.view.ui.DetailActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel.DETAIL_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.detail.viewmodel.DetailViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.state.MainState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.view.ui.MainActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel.MAIN_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.main.viewmodel.MainViewModel
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.state.SplashState
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.view.ui.SplashActivity
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel.SPLASH_VIEW_MODEL_TAG
import org.deafsapps.android.githubbrowser.presentationlayer.feature.splash.viewmodel.SplashViewModel
import javax.inject.Named

@ExperimentalCoroutinesApi
@Module(subcomponents = [SplashComponent::class, MainComponent::class, DetailComponent::class])
object PresentationlayerModule

interface SplashComponentFactoryProvider {
    fun provideSplashComponentFactory(): SplashComponent.Factory
}

@ExperimentalCoroutinesApi
@ActivityScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity)

}

@ExperimentalCoroutinesApi
@Module
class SplashModule {

    @ActivityScope
    @Provides
    @Named(SPLASH_VIEW_MODEL_TAG)
    fun provideSplashViewModel(viewModel: SplashViewModel): @JvmSuppressWildcards BaseMvvmViewModel<BaseDomainLayerBridge.None, SplashState> =
        viewModel

}

interface MainComponentFactoryProvider {
    fun provideMainComponentFactory(): MainComponent.Factory
}

@ExperimentalCoroutinesApi
@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)

}

@ExperimentalCoroutinesApi
@Module
class MainModule {

    @ActivityScope
    @Provides
    @Named(MAIN_VIEW_MODEL_TAG)
    fun provideMainViewModel(viewModel: MainViewModel): @JvmSuppressWildcards BaseMvvmViewModel<MainDomainLayerBridge<List<DataRepoBo>>, MainState> =
        viewModel

}

interface DetailComponentFactoryProvider {
    fun provideDetailComponentFactory(): DetailComponent.Factory
}

@ExperimentalCoroutinesApi
@ActivityScope
@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(activity: DetailActivity)

}

@ExperimentalCoroutinesApi
@Module
class DetailModule {

    @ActivityScope
    @Provides
    @Named(DETAIL_VIEW_MODEL_TAG)
    fun provideDetailViewModel(viewModel: DetailViewModel): @JvmSuppressWildcards BaseMvvmViewModel<BaseDomainLayerBridge.None, DetailState> =
        viewModel

}