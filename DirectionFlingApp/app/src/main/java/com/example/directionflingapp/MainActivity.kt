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
import android.widget.Toast

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, SensorEventListener {

    private lateinit var gestureDetector: GestureDetector
    private var sensorManager: SensorManager? = null
    private var lastShakeTime: Long = 0
    private var lastX: Float = 0.0f
    private var lastY: Float = 0.0f
    private var lastZ: Float = 0.0f

    private val shakeThreshold = 800 // Hardcoded for now

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this, this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent): Boolean = false

    override fun onShowPress(p0: MotionEvent) {}

    override fun onSingleTapUp(p0: MotionEvent): Boolean = false

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean = false

    override fun onLongPress(p0: MotionEvent) {}

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val diffX = e2.x - e1.x
        val diffY = e2.y - e1.y

        if (kotlin.math.abs(diffX) > kotlin.math.abs(diffY)) {
            if (diffX > 0) {
                // Right fling
                startActivity(Intent(this, EastActivity::class.java))
            } else {
                // Left fling
                startActivity(Intent(this, WestActivity::class.java))
            }
        } else {
            if (diffY > 0) {
                // Down fling
                startActivity(Intent(this, SouthActivity::class.java))
            } else {
                // Up fling
                startActivity(Intent(this, NorthActivity::class.java))
            }
        }

        return true
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
        Toast.makeText(this, getString(R.string.device_shaken), Toast.LENGTH_SHORT).show()
    }
}