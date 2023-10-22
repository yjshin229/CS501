package com.example.hangman

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HangmanViewModelTest {
    val savedStateHandle = SavedStateHandle()
    val hangmanViewModel = HangmanViewModel()

    @Test
    fun verifyStartGame() {
        hangmanViewModel.startGame()

        assertNotEquals("", hangmanViewModel.answer)
        assertNotEquals("", hangmanViewModel.hint)
        assertEquals(0, hangmanViewModel.currentTries)
        assertTrue(hangmanViewModel.isPlaying)
        assertFalse(hangmanViewModel.hasWon)
        assertFalse(hangmanViewModel.showFirstHint)
        assertEquals(R.drawable.hang0, hangmanViewModel.drawable)
        assertTrue(hangmanViewModel.lettersUsed.isEmpty())
    }

    @Test
    fun guessLetter() {
        hangmanViewModel.answer = "apple"
        hangmanViewModel.underscoreWord = "_____"

        hangmanViewModel.guessLetter('a', false)

        assertEquals("a____", hangmanViewModel.underscoreWord)
        assertFalse(hangmanViewModel.hasWon)
    }
}
