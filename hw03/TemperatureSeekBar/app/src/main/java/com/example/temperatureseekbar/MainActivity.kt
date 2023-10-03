package com.example.temperatureseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import com.example.temperatureseekbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var celsius = 0.00
    private var fahrenheit = 32.00
    var isUserInteracting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fahrenheitSeekBar = binding.fahrenheitSeekbar
        val celsiusSeekBar = binding.celsiusSeekbar

        val celsiusSeekBarChangeListener = object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                if (fromUser && !isUserInteracting) {
                    isUserInteracting = true
                    celsius = progress.toDouble()
                    binding.celsiusValue.text = String.format("%.2f",celsius)
                    calculateFahrenheit()
                    if(fahrenheit > 200){
                        binding.fahrenheitSeekbar.progress = 200
                    }else{
                        binding.fahrenheitSeekbar.progress = fahrenheit.toInt()
                    }
                    binding.fahrenheitValue.text = String.format("%.2f",fahrenheit)
                    isUserInteracting = false
                }
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {


            }
        }

        val fahrenheitSeekBarChangeListener = object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                if (fromUser && !isUserInteracting) {
                    isUserInteracting = true
                    fahrenheit = progress.toDouble()
                    binding.fahrenheitValue.text = String.format("%.2f", fahrenheit)
                    calculateCelsius()
                    if(celsius < 0){
                        binding.celsiusSeekbar.progress = 0
                    }else{
                        binding.celsiusSeekbar.progress = celsius.toInt()
                    }
                    binding.celsiusValue.text = String.format("%.2f",celsius)
                    isUserInteracting = false
                }
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        }

        celsiusSeekBar.setOnSeekBarChangeListener(celsiusSeekBarChangeListener)
        fahrenheitSeekBar.setOnSeekBarChangeListener(fahrenheitSeekBarChangeListener)

    }

    private fun calculateFahrenheit(){
        fahrenheit =  (celsius * (9.00/5)) + 32.00
    }
    private fun calculateCelsius(){
        celsius =  (5.00/9) * (fahrenheit - 32.00)

    }
}