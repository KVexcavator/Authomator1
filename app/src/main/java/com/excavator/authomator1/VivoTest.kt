package com.excavator.authomator1

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VivoTest {
    private val device: UiDevice

    init {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
    }

    @Before
    fun setUp() {
        // Press Home key before running the test
        device.pressHome()
    }

    //  cd .\Users\Public\Documents\tools\bin\
    //
    @Test
    fun validateVivo() {
        // Click on element with text "AIMP"
        val ampt = device.wait(Until.findObject(By.text("AIMP")), 2000)
        ampt.click()
    }
}