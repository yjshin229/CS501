package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.hangman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val hangmanViewModel: HangmanViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newGameButton.setOnClickListener{
            startGame()
        }
        updateUI()

        binding.lettersLayout.children.forEach { letterView ->
            if(letterView is TextView){
                letterView.setOnClickListener {
                    hangmanViewModel.guessLetter(letterView.text[0])
                    updateUI()
                    letterView.visibility = View.INVISIBLE
                }
            }
        }

    }

    private fun startGame(){
        binding.gameLostTextView.visibility = View.GONE
        binding.gameWonTextView.visibility = View.GONE
        hangmanViewModel.startGame()
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI()
        hangmanViewModel.startGame()
    }

    private fun updateUI() {
        binding.lettersLayout.children.forEach { letterView ->
            if(letterView is TextView){
                for (item in hangmanViewModel.lettersUsed) {
                    if(letterView.text == item)
                        letterView.visibility = View.INVISIBLE
                    }
            }
        }
        if(hangmanViewModel.isPlaying){
            binding.answerTextView.text = hangmanViewModel.underscoreWord
            binding.hangmanImage.setImageDrawable(ContextCompat.getDrawable(this, hangmanViewModel.drawable))
        }else{
            if(hangmanViewModel.hasWon){
                showGameWon(hangmanViewModel.answer)
            }else{
                showGameLost(hangmanViewModel.answer)
            }
        }
    }

    private fun showGameLost(wordToGuess: String) {
        binding.answerTextView.text = wordToGuess
        binding.gameLostTextView.visibility = View.VISIBLE
        binding.hangmanImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hang6))
    }

    private fun showGameWon(wordToGuess: String) {
        binding.answerTextView.text = wordToGuess
        binding.gameWonTextView.visibility = View.VISIBLE
    }

}
