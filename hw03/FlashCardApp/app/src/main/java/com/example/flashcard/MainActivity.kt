package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.flashcard.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val flashCardViewModel: FlashCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(flashCardViewModel.questionGenerated){
            binding.generateButton.isEnabled = false
            binding.submitButton.isEnabled = true
            displayQuestion()
        }

        binding.generateButton.setOnClickListener{
            generateQuestions()
            binding.generateButton.isEnabled = false
            binding.submitButton.isEnabled = true
            displayQuestion()
            flashCardViewModel.questionGenerated = true
        }
        binding.submitButton.setOnClickListener{
            if(binding.submitEdittext.text.isEmpty()){
                Toast.makeText(
                    this,
                    R.string.empty_input,
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                flashCardViewModel.submittedAnswers[flashCardViewModel.questionIndex] = binding.submitEdittext.text.toString().toInt()
                if(flashCardViewModel.questionIndex !=9){
                    binding.submitEdittext.text.clear()
                    flashCardViewModel.questionIndex ++
                    displayQuestion()
                }else{
                    flashCardViewModel.questionIndex = 0
                    binding.generateButton.isEnabled = true
                    binding.submitButton.isEnabled = false
                    resetQuestion()
                    getResult()
                    flashCardViewModel.questionGenerated = false
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
        binding.firstNumber.text = flashCardViewModel.firstNumbers[flashCardViewModel.questionIndex].toString()
        binding.secondNumber.text = flashCardViewModel.secondNumbers[flashCardViewModel.questionIndex].toString()
        binding.operator.text = if (flashCardViewModel.isAddition[flashCardViewModel.questionIndex] ) "+" else "-"
        if(flashCardViewModel.isAddition[flashCardViewModel.questionIndex]) binding.additionRadio.isChecked = true else binding.subtractionRadio.isChecked = true
        binding.questionIndexView.text =(flashCardViewModel.questionIndex + 1).toString()
    }

    private fun resetQuestion(){
        binding.firstNumber.text = ""
        binding.secondNumber.text = ""
        binding.operator.text = ""
        binding.submitEdittext.text.clear()
        binding.additionRadio.isChecked = false
        binding.subtractionRadio.isChecked = false
        binding.questionIndexView.text =(flashCardViewModel.questionIndex + 1).toString()
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
        if(flashCardViewModel.isAddition[index]){
            return arr[0] + arr[1]
        }else{
            return arr[0] - arr[1]
        }
    }

    private fun generateQuestions(){
        for (i in 0..9){
            val operator = getOperator()
            flashCardViewModel.isAddition[i] = operator
            val ints = getTwoRandom()
            val answer = calculateAnswer(ints, i)
            flashCardViewModel.firstNumbers[i] = ints[0]
            flashCardViewModel.secondNumbers[i] = ints[1]
            flashCardViewModel.answerKeys[i] = answer
        }
    }

    private fun getResult(){
        var score = 0

        for(i in 0..9){
            if(flashCardViewModel.answerKeys[i] == flashCardViewModel.submittedAnswers[i]){
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