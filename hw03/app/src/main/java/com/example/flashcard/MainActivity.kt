package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.flashcard.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var answer_keys = IntArray(10)
    var first_numbers = IntArray(10)
    var second_numbers = IntArray(10)
    var submitted_answers = IntArray(10)
    var question_index = 0
    var is_addition = BooleanArray(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener{
            generateQuestions()
            binding.generateButton.isEnabled = false
        }

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
        if(is_addition[index]){
            return arr[0] + arr[1]
        }else{
            return arr[0] - arr[1]
        }
    }

    private fun generateQuestions(){
        for (i in 0..9){
            val operator = getOperator()
            is_addition[i] = operator
            val ints = getTwoRandom()
            val answer = calculateAnswer(ints, i)
            first_numbers[i] = ints[0]
            second_numbers[i] = ints[1]
            answer_keys[i] = answer
        }
    }

}