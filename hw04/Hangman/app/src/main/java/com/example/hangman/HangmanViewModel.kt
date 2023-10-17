package com.example.hangman

import android.app.GameState
import androidx.lifecycle.ViewModel

private const val TAG = "HangmanViewModel"

class HangmanViewModel : ViewModel() {
    private var answer = ""
    private var hint = ""
    private var lettersUsed: String = ""
    private var wrongAttempts = 0
    private var hintNum = 0
    private var maxTries = 6
    private var currentTries = 0
    lateinit var underscoreWord: String
    private var drawable:Int = R.drawable.hang0
    var isPlaying = false
    var hasWon = false
    private val wordDictionary: Map<String, List<String>> = mapOf(
        "fruit" to listOf("apple", "banana", "kiwi", "grape", "orange"),
        "country" to listOf("switzerland", "sweden", "china", "canada", "Italy"),
        "animal" to listOf("gorilla", "sloth", "giraffe", "anteater", "eagle"),
        "color" to listOf("purple", "skyblue", "babypink", "Turquoise", "Maroon"),
        "sports" to listOf("basketball", "volleyball", "badminton", "gymnastics", "icehockey")
    )

    fun guessLetter(letter: Char) {
        // Implement logic
        // Update _wrongAttempts and _currentWordState as necessary
    }

    fun startGame(){
        val currentKey =  wordDictionary.keys.random()
        val currentWord = wordDictionary[currentKey]?.random()
        if (currentWord != null) {
            generateUnderscores(currentWord)
        }
        answer = currentWord!!
        hint = currentKey
        hintNum = 0
        wrongAttempts = 0
        isPlaying = true
    }

    private fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { _ -> sb.append("_")}
        underscoreWord = sb.toString()
    }

}
