package es.plexus.android.plexuschuck.presentationlayer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import es.plexus.android.plexuschuck.domainlayer.di.domainLayerModule
import es.plexus.android.plexuschuck.domainlayer.domain.JokeBoWrapper
import es.plexus.android.plexuschuck.domainlayer.feature.main.MAIN_DOMAIN_BRIDGE_TAG
import es.plexus.android.plexuschuck.domainlayer.feature.main.MainDomainLayerBridge
import es.plexus.android.plexuschuck.presentationlayer.di.presentationLayerModule
import es.plexus.android.plexuschuck.presentationlayer.feature.main.view.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest : KoinTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    private lateinit var mockBridge: MainDomainLayerBridge<JokeBoWrapper>

    @Before
    fun setUp() {
        mockBridge = mock()
        startKoin {
            modules(listOf(
                presentationLayerModule, domainLayerModule,
                module(override = true) {
                    factory(named(name = MAIN_DOMAIN_BRIDGE_TAG)) { mockBridge }
                }
            ))
        }
        activityRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun whenActivityStartsProgressBarIsDisplayed() {
        onView(withId(R.id.pbLoading))
            .check(matches(isDisplayed()))
    }

}