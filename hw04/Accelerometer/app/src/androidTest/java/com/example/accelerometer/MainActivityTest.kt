package com.example.accelerometer

import android.widget.SeekBar
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Test
    fun testInitialThresholdValue() {
        onView(withId(R.id.threshold_text)).check(matches(withText("Threshold: 25")))
    }
    @Test
    fun testSeekBarInteraction() {

        scenario.onActivity { activity ->
            val seekBar = activity.findViewById<SeekBar>(R.id.seekbar)
            seekBar.progress = 50
        }

        onView(withId(R.id.threshold_text)).check(matches(withText("Threshold: 50")))
    }

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}