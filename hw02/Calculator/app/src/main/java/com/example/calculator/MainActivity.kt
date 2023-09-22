package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.math.RoundingMode
import javax.script.ScriptEngineManager
import javax.script.ScriptException
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var expression : String

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
                binding.inputText.setSelection(binding.inputText.text.length)
            }
        }
        binding.buttonSub.setOnClickListener {
            expression = binding.inputText.text.toString() + "-"
            binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
            binding.inputText.setSelection(binding.inputText.text.length)

        }
        binding.buttonMul.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                expression = binding.inputText.text.toString() + "*"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
                binding.inputText.setSelection(binding.inputText.text.length)

            }
        }
        binding.buttonDiv.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                expression = binding.inputText.text.toString() + "/"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
                binding.inputText.setSelection(binding.inputText.text.length)

            }
        }
        binding.buttonSqrt.setOnClickListener {
            if(binding.inputText.text.isEmpty()){
                showToast("You need to enter a number first to use SquareRoot")
            }else{
                val lastNumberLength = findLastNumber(binding.inputText.text.toString())
                expression = binding.inputText.text.toString().drop(lastNumberLength) + "sqrt(" + binding.inputText.text.toString().takeLast(lastNumberLength) + ")"
                binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
                binding.inputText.setSelection(binding.inputText.text.length)

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
            if((view as Button).text == "0" && binding.inputText.text.toString().last() == '/' ){
                showToast("Dividing a number with a 0 leads to an calculation error!")
            }
            expression = binding.inputText.text.toString() + (view as Button).text
            binding.inputText.setText(expression, TextView.BufferType.EDITABLE)
        }
        binding.inputText.setSelection(binding.inputText.text.length)
    }

    private fun getResult(){
        val engine = ScriptEngineManager().getEngineByName("rhino")
        val evalExp = binding.inputText.text.toString().replace("sqrt", "Math.sqrt")
        val df = DecimalFormat("#.#####")
        df.roundingMode = RoundingMode.FLOOR
        try{
            val tempRes = engine.eval(evalExp)

            if(tempRes is Number){
                binding.resultText.text = df.format(tempRes).toString()
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
        var count = 0
        for(i in s.length-1 downTo 0){
            if(s[i].toString() != "+" || s[i].toString() != "-" || s[i].toString() != "*" || s[i].toString() != "/" || s[i].toString() != ")" ){
                count ++
            }else{
                break
            }
        }
        return count
    }
}