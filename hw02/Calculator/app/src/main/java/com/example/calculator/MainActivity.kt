package com.example.calculator

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import javax.script.ScriptEngineManager
import javax.script.ScriptException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var expression : String
    private lateinit var lastNumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //숫자버튼을 누른다 -> temp에 들어감
        //오퍼레이터를 누르면 temp에 있던거 text에 셋 시키고 temp에 operator넣어줌.
        //temp에 이미 오퍼레이터가 있는데 또 오퍼레이터 누르면 temp에 다시 넣어주기
        //temp에 오퍼레이터가 있는 상태에서 숫자를 누르면 temp -> text 그리고 숫자 temp에 넣어주기
        //equal 누르면 계산하고 res에 보여주고 text,temp 초기화
        //text에 아무것도 없는 상태에서 오퍼레이터 누르면 무시!!!!!

        //detect backspace

        binding.inputText.isActivated = true

        binding.buttonAdd.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                expression = binding.inputText.text.toString() + "+"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
            }
        }
        binding.buttonSub.setOnClickListener {
            expression = binding.inputText.text.toString() + "-"
            binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
        }
        binding.buttonMul.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                expression = binding.inputText.text.toString() + "*"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
            }
        }
        binding.buttonDiv.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                expression = binding.inputText.text.toString() + "/"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
            }
        }
        binding.buttonSqrt.setOnClickListener {
            if(binding.inputText.text.isEmpty()){
                showToast("You need to enter a number first to use SquareRoot")
            }else{
//                val stringBuilder = StringBuilder(binding.inputText.text.toString())
//                val lastNumber = binding.inputText.text.toString()
                val lastNumberLength = findLastNumber(binding.inputText.toString())
                expression = binding.inputText.text.toString().drop(lastNumberLength) + "sqrt($lastNumber)"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
            }
        }
        binding.buttonRes.setOnClickListener {
            if(binding.resultText.visibility == View.VISIBLE){
                binding.resultText.text = ""
                binding.inputText.setText("")
                binding.resultText.visibility = View.INVISIBLE
            }else{
                getResult()
            }

        }

    }
    fun onPressNumber(view: View){
        if(binding.resultText.visibility == View.VISIBLE){
            binding.resultText.text = ""
            binding.inputText.setText("")
            binding.resultText.visibility = View.INVISIBLE
        }else {
            expression = binding.inputText.text.toString() + (view as Button).text
            binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
        }
    }

    private fun getResult(){
        val engine = ScriptEngineManager().getEngineByName("rhino")
        try{
            val tempRes = engine.eval(binding.inputText.text.toString())

            if(tempRes is Number){
                binding.resultText.text = tempRes.toString()
            }else{
                binding.resultText.text = ""
            }
        }catch (err: ScriptException){
            showToast("There is an error in your expression: $err")
        }
        binding.resultText.visibility = View.VISIBLE

    }

    private fun showToast(s: String){
        Toast.makeText(
            this,
            s,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun findLastNumber(s:String): Int{
        for(i in (0..s.lastIndex).reversed()){
            if(s[i].toString().toDouble() is Number){
                lastNumber = s[i] + lastNumber
            }else{
                break
            }
        }
        return lastNumber.length
    }
}