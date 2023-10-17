package com.example.directionflingapp

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast

class SouthActivity : AppCompatActivity(), GestureDetector.OnGestureListener, SensorEventListener {

    private lateinit var gestureDetector: GestureDetector
    private var sensorManager: SensorManager? = null
    private var lastShakeTime: Long = 0
    private var lastX: Float = 0.0f
    private var lastY: Float = 0.0f
    private var lastZ: Float = 0.0f
    private val shakeThreshold = 100 // Hardcoded for now

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_south)

        gestureDetector = GestureDetector(this, this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        Toast.makeText(this, getString(R.string.direction_south), Toast.LENGTH_SHORT).show()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (velocityY < 0) {
            startActivity(Intent(this, MainActivity::class.java))
            return true
        }
        return false
    }

    override fun onDown(p0: MotionEvent): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {}

    override fun onScroll(
        p0: MotionEvent,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent) {}

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val currentTime = System.currentTimeMillis()

            if ((currentTime - lastShakeTime) > 100) {
                val diffTime = currentTime - lastShakeTime
                lastShakeTime = currentTime

                val speed = kotlin.math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000
                if (speed > shakeThreshold) {
                    onShake()
                }

                lastX = x
                lastY = y
                lastZ = z
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


    private fun onShake() {
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        val imgView: ImageView = findViewById(R.id.south_cat_image)
        imgView.startAnimation(shake)
    }


}