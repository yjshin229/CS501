package com.example.accelerometer

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.sqrt

class MovementTest{
    private fun calculateNetAccelerationWithoutGravity(x: Float, y: Float, z: Float, gravity: FloatArray): Float {
        val linearAccelerationX = x - gravity[0]
        val linearAccelerationY = y - gravity[1]
        val linearAccelerationZ = z - gravity[2]

        return sqrt(linearAccelerationX * linearAccelerationX +
                linearAccelerationY * linearAccelerationY +
                linearAccelerationZ * linearAccelerationZ)
    }
    @Test
    fun calculationOfNetAcceleration() {
        val gravity = floatArrayOf(9.6f, 9.6f, 9.6f)
        val result = calculateNetAccelerationWithoutGravity(10f, 10f, 10f, gravity)
        assertEquals(0.6928f, result, 0.001f)
    }

    @Test
    fun detectionOfSignificantMovement() {
        val threshold = 0.5f
        val netAcceleration = 0.7f
        assertTrue(netAcceleration > threshold)
    }
}
