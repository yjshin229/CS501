package com.example.flashlight

import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.*

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import com.example.flashlight.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun givenInputON_whenTyped_thenToggleShouldBeChecked() {
        onView(withId(R.id.actionTextBox)).perform(typeText("ON"))
        onView(withId(R.id.toggleFlashlight)).check(matches(isChecked()))
    }

    @Test
    fun givenInputOFF_whenTyped_thenToggleShouldNotBeChecked() {
        onView(withId(R.id.actionTextBox)).perform(typeText("OFF"))
        onView(withId(R.id.toggleFlashlight)).check(matches(not(isChecked())))
    }
}
