package com.example.flashcard

import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "FlashCardViewModel"
class FlashCardViewModel : ViewModel(){
    var answerKeys = IntArray(10)
    var firstNumbers = IntArray(10)
    var secondNumbers = IntArray(10)
    var submittedAnswers = IntArray(10)
    var questionIndex = 0
    var isAddition = BooleanArray(10)
    var questionGenerated = false

}