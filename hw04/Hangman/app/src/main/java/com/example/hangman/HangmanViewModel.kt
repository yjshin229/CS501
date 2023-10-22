package com.example.hangman

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import java.util.Locale

private const val TAG = "HangmanViewModel"

class HangmanViewModel : ViewModel() {
    var answer = ""
    var hint = ""
    var lettersUsed = mutableListOf<String>()
    private var hintNum = 0
    private var maxTries = 6
    var currentTries = 0
    lateinit var underscoreWord: String
    var drawable:Int = R.drawable.hang0
    var isPlaying = false
    var hasWon = false
    var firstPlay = true
    var showFirstHint = false

    private val wordDictionary: Map<String, List<String>> = mapOf(
        "fruit" to listOf("apple", "banana", "kiwi", "grape", "orange"),
        "country" to listOf("switzerland", "sweden", "china", "canada", "Italy"),
        "animal" to listOf("gorilla", "sloth", "giraffe", "anteater", "eagle"),
        "color" to listOf("purple", "skyblue", "babypink", "Turquoise", "Maroon"),
        "sports" to listOf("basketball", "volleyball", "badminton", "gymnastics", "icehockey")
    )

    fun guessLetter(letter: Char, fromHint: Boolean) {
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
            if(!fromHint)currentTries++
            drawable = getHangmanDrawable()
        }

        if(currentTries == maxTries){
            isPlaying = false
            hasWon = false
        }

        underscoreWord = finalUnderscoreWord
        if (underscoreWord.lowercase() == answer){
            isPlaying = false
            hasWon = true
        }
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
        currentTries = 0
        lettersUsed.clear()
        isPlaying = true
        showFirstHint = false
        drawable = getHangmanDrawable()

    }
    fun getHangmanDrawable(): Int {
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

    fun getHint():Int{
        var retval = 0
        when (hintNum) {
            0 -> {
                if(currentTries == maxTries -1){
                    isPlaying = false
                    hasWon = false
                }
                retval = 1
                currentTries ++
                hintNum ++
            }
            1 -> {
                if(currentTries == maxTries -1){
                    isPlaying = false
                    hasWon = false
                }
                hideLetters()
                retval = 2
                currentTries ++
                hintNum ++
            }
            2 -> {
                if(currentTries == maxTries -1){
                    isPlaying = false
                    hasWon = false
                }
                showVowels()
                retval = 3
                currentTries ++
                hintNum ++
            }
            else -> {
                retval = -1
            }
        }

        drawable = getHangmanDrawable()
        return retval
    }
    private fun hideLetters() {
        val numToRemove =( 26 - lettersUsed.size)/2
        Log.d("numToRemove", numToRemove.toString())
        var numRemoved = 0
            for (letter in 'A'..'Z') {
                if (letter.toString().lowercase() !in answer.lowercase()) {
                    lettersUsed.add(letter.toString())
                    numRemoved++

                    Log.d("letter", letter.toString())
                    Log.d("numRemoved", numRemoved.toString())
                    Log.d("answer", answer)
                    Log.d("is in?", "true")
                }
                if (numRemoved == numToRemove){
                    return
                }
            }
        }

    private fun showVowels(){
        val vowels = "AEIOU"

        for (vowel in vowels) {
            if(vowel.toString() !in lettersUsed){
                guessLetter(vowel, true)
            }

        }
    }


}
