package com.example.flashcard

import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FlashCardViewModelTest{
    @Test
    fun providesCorrectAnswerAddition(){
        val savedStateHandle = SavedStateHandle()
        val flashCardViewModel = FlashCardViewModel(savedStateHandle)
        for(i in 1..9){
            if(flashCardViewModel.isAddition[i])
                assertEquals(flashCardViewModel.answerKeys[i],flashCardViewModel.firstNumbers[i] +flashCardViewModel.secondNumbers[i] )
        }
    }
    @Test
    fun providesCorrectAnswerSubtraction(){
        val savedStateHandle = SavedStateHandle()
        val flashCardViewModel = FlashCardViewModel(savedStateHandle)
        for(i in 1..9){
            if(!flashCardViewModel.isAddition[i])
                assertEquals(flashCardViewModel.answerKeys[i],flashCardViewModel.firstNumbers[i] +flashCardViewModel.secondNumbers[i] )
        }
    }
}