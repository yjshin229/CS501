package com.example.hangman

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel

private const val TAG = "HangmanViewModel"

class HangmanViewModel : ViewModel() {
    var answer = ""
    private var hint = ""
    var lettersUsed = mutableListOf<String>()
    private var wrongAttempts = 0
    private var hintNum = 0
    private var maxTries = 6
    private var currentTries = 0
    lateinit var underscoreWord: String
    var drawable:Int = R.drawable.hang0
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
        lettersUsed.add(letter.toString())
        val indexes = mutableListOf<Int>()

        answer.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = underscoreWord
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
            drawable = getHangmanDrawable()
        }

        underscoreWord = finalUnderscoreWord
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
        lettersUsed.clear()
        isPlaying = true

    }
    private fun getHangmanDrawable(): Int {
        return when (currentTries) {
            0 -> R.drawable.hang0
            1 -> R.drawable.hang1
            2 -> R.drawable.hang2
            3 -> R.drawable.hang3
            4 -> R.drawable.hang4
            5 -> R.drawable.hang5
            6 -> R.drawable.hang6
            else -> R.drawable.hang6
        }
    }
    private fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { _ -> sb.append("_")}
        underscoreWord = sb.toString()
    }


}
