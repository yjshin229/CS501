package com.bignerdranch.android.sunset

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bignerdranch.android.sunset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentSunY: Float = 0.0F
    private var currentSkyColor: Int = 0
    private var isAnimationCancelled = false
    private enum class SunState {
        RISEN, SET, RISING, SETTING
    }
    private var sunsetAnimatorSet: AnimatorSet? = null
    private var sunriseAnimatorSet: AnimatorSet? = null
    private var sunState = SunState.RISEN


    private val blueSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.blue_sky)
    }
    private val sunsetSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.sunset_sky)
    }

    private val nightSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.night_sky)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scene.setOnClickListener {
            Log.d("currentState", sunState.toString())

            when (sunState) {
                SunState.SET -> {
                    sunState = SunState.RISING
                    pulsateSunAnimation()
                    startSunriseAnimation(reverse = false)
                }
                SunState.RISEN -> {
                    sunState = SunState.SETTING
                    pulsateSunAnimation()
                    startSunsetAnimation(reverse = false)

                }
                SunState.RISING -> {
                    pulsateSunAnimation()
                    reverseSunAnimation()
                }
                SunState.SETTING -> {
                    pulsateSunAnimation()
                    reverseSunAnimation()
                }
            }
        }
    }

    private fun startSunsetAnimation(reverse: Boolean) {
        val sunYStart = binding.sun.top.toFloat()
        val sunYEnd = binding.sky.height.toFloat()

        if(reverse){
            isAnimationCancelled = true
            currentSunY = binding.sun.y
            currentSkyColor = (binding.sky.background as? ColorDrawable)?.color ?: blueSkyColor
        }else{
            isAnimationCancelled = false
            currentSunY = sunYStart
            currentSkyColor = blueSkyColor
        }

        val heightAnimator = ObjectAnimator
            .ofFloat(binding.sun, "y", currentSunY, sunYEnd)
            .setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator()

        val skipSunsetSkyAnimator = currentSkyColor != blueSkyColor
        val startColorForNightSkyAnimator = if (skipSunsetSkyAnimator) currentSkyColor else sunsetSkyColor

        val sunsetSkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", currentSkyColor, sunsetSkyColor)
            .setDuration(if (skipSunsetSkyAnimator) 0 else 3000)
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())

        val nightSkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", startColorForNightSkyAnimator, nightSkyColor)
            .setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())

        val animatorSet = AnimatorSet()
        animatorSet.play(heightAnimator)
            .with(sunsetSkyAnimator)
            .before(nightSkyAnimator)

        sunriseAnimatorSet = animatorSet
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (!isAnimationCancelled) {
                    sunState = SunState.SET
                }
            }
        })
        animatorSet.start()
    }

    private fun startSunriseAnimation(reverse: Boolean) {
        val sunYEnd = binding.sun.top.toFloat()
        val sunYStart = binding.sky.height.toFloat()

        if(reverse){
            isAnimationCancelled = true
            currentSunY = binding.sun.y
            currentSkyColor = (binding.sky.background as? ColorDrawable)?.color ?: nightSkyColor
        }else{
            isAnimationCancelled = false
            currentSunY = sunYStart
            currentSkyColor = nightSkyColor
        }
        val skipSunriseSkyAnimator = currentSkyColor != nightSkyColor
        val startColorForDaySkyAnimator = if (skipSunriseSkyAnimator) currentSkyColor else sunsetSkyColor

        val heightAnimator = ObjectAnimator
            .ofFloat(binding.sun, "y", currentSunY, sunYEnd)
            .setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator()

        val sunriseSkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor",currentSkyColor, sunsetSkyColor)
            .setDuration(if(skipSunriseSkyAnimator) 0 else 1500)
        sunriseSkyAnimator.setEvaluator(ArgbEvaluator())

        val daySkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", startColorForDaySkyAnimator, blueSkyColor )
            .setDuration(3000)
        daySkyAnimator.setEvaluator(ArgbEvaluator())

        val animatorSet = AnimatorSet()
        animatorSet.play(sunriseSkyAnimator)
            .with(heightAnimator)
            .before(daySkyAnimator)

        sunriseAnimatorSet = animatorSet
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (!isAnimationCancelled) {
                    sunState = SunState.RISEN
                }
            }
        })
        animatorSet.start()
    }

    private fun pulsateSunAnimation(){
        val pulsateAnimator = ObjectAnimator
            .ofPropertyValuesHolder(binding.sun,
                PropertyValuesHolder.ofFloat("ScaleX", 1.2f),
                PropertyValuesHolder.ofFloat("ScaleY", 1.2f))
            .setDuration(300)

        pulsateAnimator.repeatCount = ObjectAnimator.INFINITE
        pulsateAnimator.repeatMode = ObjectAnimator.REVERSE

        pulsateAnimator.interpolator = AccelerateInterpolator()
        pulsateAnimator.start()
    }

    private fun reverseSunAnimation() {
        when (sunState) {
            SunState.RISING -> {
                sunState = SunState.SETTING
                sunriseAnimatorSet?.cancel()
                startSunsetAnimation(reverse = true)
            }
            SunState.SETTING -> {
                sunState = SunState.RISING
                sunsetAnimatorSet?.cancel()
                startSunriseAnimation(reverse = true)
            }
            else -> {}
        }
    }
}
