package com.example.directionflingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent

class SouthActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_north)

        gestureDetector = GestureDetector(this, this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (e2.y > e1.y) {
            // Downward fling detected in NorthActivity, returning to MainActivity
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

}