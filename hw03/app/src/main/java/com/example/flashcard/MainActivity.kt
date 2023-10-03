package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.flashcard.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var answer_keys = emptyArray<Int>()
    var first_numbers = emptyArray<Int>()
    var second_numbers = emptyArray<Int>()
    var submitted_answers = emptyArray<Int>()
    var question_index = 0
    var is_addition = emptyArray<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener{
            generateQuestions()
            binding.generateButton.isEnabled = false
        }

    }

    private fun getTwoRandom() : Array<Int>{
        val ints = emptyArray<Int>()
        ints[0] = Random.nextInt(1,100)
        ints[1] = Random.nextInt(1,20)
        return ints
    }

    private fun getOperator(): Boolean{

        return Random.nextBoolean()
    }

    private fun calculateAnswer(arr: Array<Int>, index: Int):Int{
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