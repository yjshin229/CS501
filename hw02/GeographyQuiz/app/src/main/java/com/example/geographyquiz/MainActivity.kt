package com.example.geographyquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.geographyquiz.databinding.ActivityMainBinding

import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener {view: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {view: View ->
            if (currentIndex == questionBank.size - 1) {
                showSnackbar(view, R.string.last_question_toast)
            } else {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()
            }
        }
        binding.previousButton.setOnClickListener {view: View ->
            if (currentIndex == 0) {
                showSnackbar(view, R.string.first_question_toast)
            } else {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
        }

        binding.cheatButton.setOnClickListener{view:View ->
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer === correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        showSnackbar(findViewById(R.id.mainView),messageResId)


    }
    private fun showSnackbar (view: View ,@StringRes textResId:Int){
        val snackbar = Snackbar.make(
            view,
            textResId,
            Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }
}