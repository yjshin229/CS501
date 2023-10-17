package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    }

    private fun resetGameState(){
    }

    private fun startGame(){
        hangmanViewModel.startGame()
    }

    private fun updateUI() {
        if(hangmanViewModel.isPlaying){
            binding.answerTextView.text = hangmanViewModel.underscoreWord
            imageView.setImageDrawable(ContextCompat.getDrawable(this, gameState.drawable))
        }
    }

}
