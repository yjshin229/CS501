package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.flashcard.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var answerKeys = IntArray(10)
    private var firstNumbers = IntArray(10)
    private var secondNumbers = IntArray(10)
    private var submittedAnswers = IntArray(10)
    private var questionIndex = 0
    private var isAddition = BooleanArray(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener{
            generateQuestions()
            binding.generateButton.isEnabled = false
            binding.submitButton.isEnabled = true
            displayQuestion()
        }
        binding.submitButton.setOnClickListener{
            if(binding.submitEdittext.text.isEmpty()){
                Toast.makeText(
                    this,
                    R.string.empty_input,
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                submittedAnswers[questionIndex] = binding.submitEdittext.text.toString().toInt()
                if(questionIndex !=9){
                    binding.submitEdittext.text.clear()
                    questionIndex ++
                    displayQuestion()
                }else{
                    questionIndex = 0
                    binding.generateButton.isEnabled = true
                    binding.submitButton.isEnabled = false
                    resetQuestion()
                    getResult()
                }
            }


        }
        binding.submitButton.setOnKeyListener{v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN){
                binding.submitButton.performClick()
                true
            }else{
                false
            }
        }

    }
    private fun displayQuestion(){
        binding.firstNumber.text = firstNumbers[questionIndex].toString()
        binding.secondNumber.text = secondNumbers[questionIndex].toString()
        binding.operator.text = if (isAddition[questionIndex] ) "+" else "-"
        if(isAddition[questionIndex]) binding.additionRadio.isChecked = true else binding.subtractionRadio.isChecked = true
        binding.questionIndexView.text =(questionIndex + 1).toString()
    }

    private fun resetQuestion(){
        binding.firstNumber.text = ""
        binding.secondNumber.text = ""
        binding.operator.text = ""
        binding.submitEdittext.text.clear()
        binding.additionRadio.isChecked = false
        binding.subtractionRadio.isChecked = false
        binding.questionIndexView.text =(questionIndex + 1).toString()
    }

    private fun getTwoRandom() : IntArray{
        val ints = IntArray(2)
        ints[0] = Random.nextInt(1,100)
        ints[1] = Random.nextInt(1,20)
        return ints
    }

    private fun getOperator(): Boolean{

        return Random.nextBoolean()
    }

    private fun calculateAnswer(arr: IntArray, index: Int):Int{
        if(isAddition[index]){
            return arr[0] + arr[1]
        }else{
            return arr[0] - arr[1]
        }
    }

    private fun generateQuestions(){
        for (i in 0..9){
            val operator = getOperator()
            isAddition[i] = operator
            val ints = getTwoRandom()
            val answer = calculateAnswer(ints, i)
            firstNumbers[i] = ints[0]
            secondNumbers[i] = ints[1]
            answerKeys[i] = answer
        }
    }

    private fun getResult(){
        var score = 0

        for(i in 0..9){
            if(answerKeys[i] == submittedAnswers[i]){
                score++
            }
        }

        val formattedText = "$score out of 10"
        Toast.makeText(
            this,
            formattedText,
            Toast.LENGTH_SHORT
        ).show()
    }



}