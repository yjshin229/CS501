package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var text: String
    private lateinit var tempText: String

    private  var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "1"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button1.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "1"
            }else if(tempText.isEmpty()){
                tempText = "1"
            }else{
                text += tempText
                tempText = "1"
            }
        }
        binding.button2.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "2"
            }else if(tempText.isEmpty()){
                tempText = "2"
            }else{
                text += tempText
                tempText = "2"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }
        binding.button0.setOnClickListener{
            if(tempTextIsNumeric()){
                tempText+= tempText + "" + "0"
            }else if(tempText.isEmpty()){
                tempText = "0"
            }else{
                text += tempText
                tempText = "0"
            }
        }

    }
    //숫자버튼을 누른다 -> temp에 들어감
    //오퍼레이터를 누르면 temp에 있던거 text에 셋 시키고 temp에 operator넣어줌.
    //temp에 이미 오퍼레이터가 있는데 또 오퍼레이터 누르면 temp에 다시 넣어주기
    //temp에 오퍼레이터가 있는 상태에서 숫자를 누르면 temp -> text 그리고 숫자 temp에 넣어주기
    //equal 누르면 계산하고 res에 보여주고 text,temp 초기화
    //text에 아무것도 없는 상태에서 오퍼레이터 누르면 무시!!!!!


    private fun tempTextIsNumeric(): Boolean{
        return tempText.toDoubleOrNull() != null
    }
    private fun checkOperator() {
        if (tempText.length == 0) {
            operator = binding.inputText.text.toString()
        }
    }

    private fun pressEqual(){
        text = ""
        tempText = ""
    }
}