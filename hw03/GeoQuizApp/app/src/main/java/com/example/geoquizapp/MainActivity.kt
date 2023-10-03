package com.example.geoquizapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.geoquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)

        val buttonsEnabled = !quizViewModel.questionsAnswered[quizViewModel.currentIndex]
        binding.trueButton.isEnabled = buttonsEnabled
        binding.falseButton.isEnabled = buttonsEnabled
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            quizViewModel.numCorrectAnswers++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        quizViewModel.questionsAnswered[quizViewModel.currentIndex] = true
        quizViewModel.numQuestionsAnswered++

        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (quizViewModel.numQuestionsAnswered == quizViewModel.questionBank.size) {
            val grade = (quizViewModel.numCorrectAnswers.toDouble() / quizViewModel.questionBank.size) * 100
            Toast.makeText(this, "Quiz complete! Your score: ${grade.toInt()}%", Toast.LENGTH_LONG).show()
        }
    }

}