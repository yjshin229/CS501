package com.example.directionflingapp

import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class SouthActivityFlingTest {

    private lateinit var activityController: ActivityController<SouthActivity>
    private lateinit var activity: SouthActivity

    @Before
    fun setup() {
        activityController = Robolectric.buildActivity(SouthActivity::class.java).create().visible()
        activity = activityController.get()
    }

    @After
    fun tearDown() {
        activityController.pause().stop().destroy()
    }

    @Test
    fun testFlingUpwardReturnsToMain() {
        onView(withId(R.id.south_cat_image)).perform(swipeUp())

        val expectedIntent = Intent(activity, MainActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity
        assertThat(actualIntent.component, equalTo(expectedIntent.component))
    }
}
