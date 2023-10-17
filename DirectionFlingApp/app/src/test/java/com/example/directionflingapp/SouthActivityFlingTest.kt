package com.example.directionflingapp

import android.view.MotionEvent
import org.junit.Assert.*
import org.junit.Test

class SouthActivityFlingTest {

    @Test
    fun testOnFlingNorthDirection() {
        val activity = SouthActivity()
        val e1 = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 100f, 200f, 0)
        val e2 = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 100f, 100f, 0)
        assertTrue(activity.onFling(e1, e2, 0f, -100f))
    }
}
