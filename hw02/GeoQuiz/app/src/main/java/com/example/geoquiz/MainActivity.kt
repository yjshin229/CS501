package com.example.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {
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

        binding.trueButton.setOnClickListener{ view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{ view: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener{view: View -> 
            if(currentIndex == questionBank.size -1){
                Toast.makeText(
                    this,
                    R.string.last_question_toast,
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()
            }
        }
        binding.previousButton.setOnClickListener{
            if(currentIndex == 0){
                Toast.makeText(
                    this,
                    R.string.first_question_toast,
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
        }

        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId =  questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer === correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Snackbar.make(findViewById(R.id.main_view), messageResId, Snackbar.LENGTH_SHORT)
            .show()
    }

}