package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        binding.getHintButton.setOnClickListener {
            val hintNum = hangmanViewModel.getHint()
            if(hintNum == 1){
                hangmanViewModel.showFirstHint = true
                binding.firstHintText.text = hangmanViewModel.hint
            }else if(hintNum == 2){
                Toast.makeText(
                    this,
                    R.string.remove_letter_hint,
                    Toast.LENGTH_SHORT
                ).show()
            }else if(hintNum == 3){
                Toast.makeText(
                    this,
                    R.string.vowel_hint,
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this,
                    R.string.hint_disabled,
                    Toast.LENGTH_SHORT
                ).show()
            }
            updateUI()
        }
        updateUI()
        
        binding.lettersLayout.children.forEach { letterView ->
            if(letterView is TextView){
                letterView.setOnClickListener {
                    if(!hangmanViewModel.isPlaying){
                        return@setOnClickListener
                    }
                    hangmanViewModel.guessLetter(letterView.text[0],false)
                    letterView.visibility = View.INVISIBLE
                    updateUI()

                }
            }
        }
        

    }

    private fun startGame(){
        hangmanViewModel.firstPlay = false
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
        if(hangmanViewModel.showFirstHint){
            binding.firstHintText.text = hangmanViewModel.hint
        }
        if(hangmanViewModel.isPlaying){
            binding.answerTextView.text = hangmanViewModel.underscoreWord
            binding.hangmanImage.setImageDrawable(ContextCompat.getDrawable(this, hangmanViewModel.drawable))
        }else{
            if(hangmanViewModel.hasWon){
                if(!hangmanViewModel.firstPlay) showGameWon(hangmanViewModel.answer)
            }else{
                if(!hangmanViewModel.firstPlay)showGameLost(hangmanViewModel.answer)
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
        binding.hangmanImage.setImageDrawable(ContextCompat.getDrawable(this, hangmanViewModel.getHangmanDrawable()
        ))
    }

}
