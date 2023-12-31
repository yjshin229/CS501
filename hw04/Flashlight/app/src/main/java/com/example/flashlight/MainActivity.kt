package com.example.flashlight

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Switch
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var cameraManager: CameraManager? = null
    private var cameraId: String? = null
    private var isFlashlightOn = false
    private var hasFlash = false
    private lateinit var mDetector: GestureDetectorCompat
    fun processInputText(input: String): String = input.uppercase(Locale.ROOT).trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashlightToggle = findViewById<Switch>(R.id.toggleFlashlight)
        val actionTextBox = findViewById<EditText>(R.id.actionTextBox)

        hasFlash = applicationContext.packageManager
            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if (!hasFlash) {
            Toast.makeText(this, "No flash available on this device.", Toast.LENGTH_SHORT).show()
            flashlightToggle.isEnabled = false
            actionTextBox.isEnabled = false
        }

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
//   
        try {
            for (id in cameraManager?.cameraIdList.orEmpty()) {
                val characteristics = cameraManager?.getCameraCharacteristics(id)
                val hasFlashUnit = characteristics?.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
                if (hasFlashUnit) {
                    cameraId = id
                    break
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error while accessing the camera.", Toast.LENGTH_SHORT).show()
        }









        actionTextBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // We don't need any action here for this app.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // We don't need any action here for this app.
            }

            override fun afterTextChanged(s: Editable) {
                when (s.toString().toUpperCase().trim()) {
                    "ON" -> {
                        setFlashlight(true)
                        flashlightToggle.isChecked = true
                    }
                    "OFF" -> {
                        setFlashlight(false)
                        flashlightToggle.isChecked = false
                    }
                }
            }
        })

        mDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                if (e2.y - e1.y > 50) { // Fling down
                    setFlashlight(false)
                    flashlightToggle.isChecked = false
                    return true
                } else if (e1.y - e2.y > 50) { // Fling up
                    setFlashlight(true)
                    flashlightToggle.isChecked = true
                    return true
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })


        flashlightToggle.setOnCheckedChangeListener { _, isChecked ->
            setFlashlight(isChecked)
            showToast(isChecked)
        }
    }

    private fun setFlashlight(state: Boolean) {
        if(!hasFlash){
            return
        }
        try {
            if (cameraId != null) { // Check if device has a camera
                cameraManager?.setTorchMode(cameraId!!, state)
                isFlashlightOn = state

                // Logging to Logcat
                if (isFlashlightOn) {
                    Log.d("FlashlightApp", "Flashlight turned ON")
                } else {
                    Log.d("FlashlightApp", "Flashlight turned OFF")
                }
            }
        } catch (e: CameraAccessException) {
            Toast.makeText(this, "Error changing flashlight state.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showToast(isFlashOn: Boolean) {
        if (isFlashOn) {
            Toast.makeText(this, "Flashlight is ON", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Flashlight is OFF", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }
}
