package com.example.hangman

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.isEmptyString

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
    fun testHintText() {
        onView(withId(R.id.newGameButton)).perform(click())
        onView(withId(R.id.getHintButton)).perform(click())

        onView(withId(R.id.firstHintText)).check(matches(withText(not(isEmptyString()))))
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