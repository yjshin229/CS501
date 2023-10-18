package com.example.directionflingapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NorthActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(NorthActivity::class.java)

    @Test
    fun testNorthCatImageDisplayed() {
        onView(withId(R.id.north_cat_image)).check(matches(isDisplayed()))
    }
}
