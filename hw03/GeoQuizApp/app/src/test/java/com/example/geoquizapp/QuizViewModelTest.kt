package com.example.geoquizapp

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun providesExpectedQuestionText() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun wrapsAroundQuestionBank() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun correctlyHandlesCheatingState() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)

        // Assume cheating on the first question
        quizViewModel.questionsCheated[0] = true

        assertTrue(quizViewModel.questionsCheated[0])  // Assert we cheated on the first question

        // Move to the next question
        quizViewModel.moveToNext()

        assertFalse(quizViewModel.questionsCheated[quizViewModel.currentIndex])  // Assert we haven't cheated on the second question

        // Cheat on the second question and verify
        quizViewModel.questionsCheated[quizViewModel.currentIndex] = true

        assertTrue(quizViewModel.questionsCheated[quizViewModel.currentIndex])  // Assert we cheated on the second question
    }
}