package com.example.flashlight

import com.example.flashlight.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {

    private val mainActivity = MainActivity()

    @Test
    fun givenLowercaseOn_whenProcessInputText_thenReturnON() {
        val result = mainActivity.processInputText("on")
        assertEquals("ON", result)
    }

    @Test
    fun givenInputWithSpaces_whenProcessInputText_thenReturnTrimmedInput() {
        val result = mainActivity.processInputText("  OFF  ")
        assertEquals("OFF", result)
    }
}
