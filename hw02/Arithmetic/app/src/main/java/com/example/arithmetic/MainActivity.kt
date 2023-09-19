package com.example.arithmetic

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.arithmetic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var checkedRadio = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =  ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener{_, checkedId ->
            when(checkedId) {
                R.id.add_button -> checkedRadio = 0
                R.id.subtract_button -> checkedRadio = 1
                R.id.multiply_button -> checkedRadio = 2
                R.id.division_button -> checkedRadio = 3
                R.id.modulus_button -> checkedRadio = 4
            }
        }

        binding.calculateButton.setOnClickListener{view : View ->
            calculate()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }

    }

    private fun calculate(){
        if(binding.firstInput.length() == 0 || binding.secondInput.length() == 0){
            showToast("inputErr")
        }else{
            var answer = "0"
            when(checkedRadio){
                0 -> {
                    answer = (binding.firstInput.text.toString().toDouble() + binding.secondInput.text.toString().toDouble()).toString()
                }
                1 -> {
                    answer = (binding.firstInput.text.toString().toDouble() - binding.secondInput.text.toString().toDouble()).toString()
                }
                2->{
                    answer = (binding.firstInput.text.toString().toDouble() * binding.secondInput.text.toString().toDouble()).toString()
                }
                3->{
                    if(binding.secondInput.text.toString().toDouble() == "0".toDouble()){
                        showToast("divisionErr")
                        answer = "Error"
                    }else{
                        answer = (binding.firstInput.text.toString().toDouble() / binding.secondInput.text.toString().toDouble()).toString()
                    }
                }
                4->{

                    if(binding.secondInput.text.toString().toDouble() == "0".toDouble()){
                        answer = "Undefined"
                    }else{
                        answer = (binding.firstInput.text.toString().toDouble() % binding.secondInput.text.toString().toDouble()).toString()
                    }
                }
            }
            binding.answerTextView.text = answer
        }

    }

    private fun showToast(err: String){
        var divisionErr = "You cannot divide by 0!"
        var inputErr = "Check your inputs!"
        Toast.makeText(
            this,
            if (err == "divisionErr") divisionErr else inputErr ,
            Toast.LENGTH_SHORT
        ).show()
    }
}