package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Math.sqrt
import javax.script.ScriptEngine

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var expression: Expression
    private lateinit var operator : String
    private lateinit var tempAns : String

    private var num1 : Double = 0.0
    private var num2 : Double = 0.0
    private var result : Double = 0.0

    private var isLastNumber =  false
    private var isError = false
    private var isLastDot = false

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

        binding.buttonAdd.setOnClickListener {
            num1 = (binding.resultText.text.toString()).toDouble()
            val primary = java.lang.String.format("%.2f", num1)
            binding.inputText.setText(primary)
            binding.resultText.text = ""
            operator = "+"
        }
        binding.buttonSub.setOnClickListener {
            num1 = (binding.resultText.text.toString()).toDouble()
            val primary = java.lang.String.format("%.2f", num1)
            binding.inputText.setText(primary)
            binding.resultText.text = ""
            operator = "-"
        }
        binding.buttonMul.setOnClickListener {
            num1 = (binding.resultText.text.toString()).toDouble()
            val primary = java.lang.String.format("%.2f", num1)
            binding.inputText.setText(primary)
            binding.resultText.text = ""
            operator = "*"
        }
        binding.buttonDiv.setOnClickListener {
            num1 = (binding.resultText.text.toString()).toDouble()
            val primary = java.lang.String.format("%.2f", num1)
            binding.inputText.setText(primary)
            binding.resultText.text = ""
            operator = "/"
        }
        binding.buttonSqrt.setOnClickListener {
            num1 = (binding.resultText.text.toString()).toDouble()
            val primary = java.lang.String.format("%.2f", num1)
            binding.inputText.setText(primary)
            binding.resultText.text = ""
            operator = "sqrt"
        }
        binding.buttonRes.setOnClickListener {
            if(operator=="+") {
                result=num1+num2;
                tempAns=String.format("%.2f",result);
                binding.resultText.text = tempAns;
                binding.inputText.setText("", TextView.BufferType.EDITABLE)
            }
            if(operator=="-") {
                result=num1-num2;
                tempAns=String.format("%.2f",result);
                binding.resultText.text = tempAns;
                binding.inputText.setText("", TextView.BufferType.EDITABLE)
            }
            if(operator=="*") {
                result=num1*num2;
                tempAns=String.format("%.2f",result);
                binding.resultText.text = tempAns;
                binding.inputText.setText("", TextView.BufferType.EDITABLE)
            }
            if(operator=="/") {
                if(num2 != 0.0){
                    result=num1/num2;
                    tempAns=String.format("%.2f",result);
                }else{
                    showToast("You cannot divide by 0!")
                    tempAns = "Err"
                }
                binding.resultText.text = tempAns;
                binding.inputText.setText("", TextView.BufferType.EDITABLE)
            }
            if(operator=="sqrt") {
                result= kotlin.math.sqrt(num2);
                tempAns=String.format("%.2f",result);
                binding.resultText.text = tempAns;
                binding.inputText.setText("", TextView.BufferType.EDITABLE)
            }
        }
    }
    fun onPressNumber(view: View){
        if(isError){
            binding.inputText.setText((view as Button).text, TextView.BufferType.EDITABLE)
            isError = false
        }else{
            if(binding.inputText.text.toString() == "0.0"){
                binding.inputText.setText((view as Button).text, TextView.BufferType.EDITABLE)
            }else{
                binding.inputText.setText(binding.inputText.text.toString() + "" + (view as Button).text)
            }
        }
        isLastNumber = true
        calculate()
    }
    fun onPressOperator(view: View){
        if(!isError && !isLastNumber){
            binding.inputText.setText(binding.inputText.text.toString() + "" + (view as Button).toString())
            isLastDot = false
            isLastNumber = false
            calculate()
        }
    }


    fun onPressEqual(view: View){
        calculate()
        binding.inputText.setText(binding.resultText.text.toString().drop(1))
    }
    private fun calculate(){
        if(isLastNumber && !isError){
            val text = binding.inputText.text.toString()
            expression = ExpressionBuilder(text).build()
        }
        try{
            val res = expression.evaluate()
            binding.resultText.visibility = View.VISIBLE
            binding.resultText.text = "=$res"
        }catch(err: ArithmeticException){
            showToast("Evaluation Error: $err")
            binding.resultText.text = "Err"
            isError = true
            isLastNumber = false
        }
    }
//    private fun getResult(s: String){
//        val engine = ScriptEngine
//    }

    private fun showToast(s: String){
        Toast.makeText(
            this,
            s,
            Toast.LENGTH_SHORT
        ).show()
    }
}