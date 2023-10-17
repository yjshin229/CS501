package com.example.hangman

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Test
    fun testStartGameUI() {
        onView(withId(R.id.newGameButton)).perform(click())
        onView(withId(R.id.answerTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.hangmanImage)).check(matches(isDisplayed()))
    }

    @Test
    fun testHintToast() {
        scenario.use {
            it.onActivity { activity ->
                onView(withId(R.id.getHintButton)).perform(click())
                onView(withText(R.string.remove_letter_hint))
                    .inRoot(withDecorView(not(activity.window.decorView)))
                    .check(matches(isDisplayed()))
            }
        }
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