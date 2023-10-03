package com.example.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun generateButtonEnabledOnLaunch(){
        onView(withId(R.id.generate_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun generateButtonDisabledOnClick(){
        onView(withId(R.id.generate_button)).perform(click())
        onView(withId(R.id.generate_button))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun displayCorrectNumberOnGenerate(){
        val savedStateHandle = SavedStateHandle()
        val flashCardViewModel = FlashCardViewModel(savedStateHandle)
        onView(withId(R.id.generate_button)).perform(click())
        onView(withId(R.id.question_index_view)).check(matches(withText("1")))
    }
}