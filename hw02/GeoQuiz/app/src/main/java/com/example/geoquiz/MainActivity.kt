package com.example.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
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
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener{
            if(currentIndex == questionBank.size -1){
                showSnackbar(R.string.last_question_toast)
            }else {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()
            }
        }
        binding.previousButton.setOnClickListener{
            if(currentIndex == 0){
                showSnackbar(R.string.first_question_toast)
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

        showSnackbar(messageResId)

//        Toast.makeText(
//            this,
//            messageResId,
//            Toast.LENGTH_SHORT
//        ).show()

    }

    private fun showSnackbar (textResId:Int){
        val snackbar = Snackbar.make(
            binding.root,
            textResId,
            Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }

}