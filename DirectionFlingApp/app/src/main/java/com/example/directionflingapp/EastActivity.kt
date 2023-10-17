package com.example.directionflingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast

class EastActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_east)

        gestureDetector = GestureDetector(this, this)

        // Displaying the string resource for North direction
        Toast.makeText(this, getString(R.string.direction_east), Toast.LENGTH_SHORT).show()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (velocityX < 0) {
            // Leftward fling detected in EestActivity, returning to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            return true
        }
        return false
    }


    override fun onDown(p0: MotionEvent): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {
        // No-op
    }

    override fun onScroll(
        p0: MotionEvent,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent) {
        // No-op
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }

    fun onShake() {
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        val imgView: ImageView = findViewById(R.id.east_cat_image) // change the ID based on the activity
        imgView.startAnimation(shake)
    }


}