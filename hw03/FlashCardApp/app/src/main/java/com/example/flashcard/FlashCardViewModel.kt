package com.example.flashcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "FlashCardViewModel"
class FlashCardViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    var answerKeys = IntArray(10)
    var firstNumbers = IntArray(10)
    var secondNumbers = IntArray(10)
    var submittedAnswers = IntArray(10)
    var questionIndex = 0
    var isAddition = BooleanArray(10)
    var questionGenerated = false

}