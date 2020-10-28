package org.deafsapps.android.githubbrowser.base

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.deafsapps.android.githubbrowser.di.ApplicationComponent
import org.deafsapps.android.githubbrowser.di.DaggerApplicationComponent
import org.deafsapps.android.githubbrowser.di.UtilsModule
import org.deafsapps.android.githubbrowser.presentationlayer.di.*

/**
 * This class implements an [Application] subclass instance which serves as entry point to the app.
 * General tool configurations such as 'LeakCanary' for memory leaks, and 'Dagger' for dependency
 * inversion are initialized here.
 *
 * @author Pablo L. Sordo Martínez
 * @since 1.0
 */
@ExperimentalCoroutinesApi
class BaseApplication : Application(), SplashComponentFactoryProvider,
    MainComponentFactoryProvider, DetailComponentFactoryProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        /*
         'ApplicationComponent' is created including all data every associated component needs.
         Specifically, 'modules' parameters refer to those which demand external variables (mostly
         'Context' instances).
         */
        appComponent = DaggerApplicationComponent.factory().create(modules = UtilsModule(ctx = this))
    }

    override fun provideSplashComponentFactory(): SplashComponent.Factory =
        appComponent.splashComponentFactory()

    override fun provideMainComponentFactory(): MainComponent.Factory =
        appComponent.mainComponentFactory()

    override fun provideDetailComponentFactory(): DetailComponent.Factory =
        appComponent.detailComponentFactory()

}