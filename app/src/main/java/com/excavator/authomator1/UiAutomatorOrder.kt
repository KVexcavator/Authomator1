package com.excavator.authomator1

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UiAutomatorOrder {
    /**
     * Run before the method with @Test annotation
     */
    @Before
    fun before() {
        Log.d(TAG, "Before")
    }

    /**
     * Run after the method with @Before annotation
     * and before methods with @After annotation
     */
    @Test
    fun test() {
        Log.d(TAG, "Test")
    }

    /**
     * Run after each method with @Test annotation
     */
    @After
    fun after() {
        Log.d(TAG, "After")
    }

    companion object {
        private const val TAG = "UiAutomatorExample"

        /**
         * Run once before other methods from [UiAutomatorOrder] class
         */
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            Log.d(TAG, "Before Class")
        }

        /**
         * Run once after other methods from [UiAutomatorOrder] class
         */
        @JvmStatic
        @AfterClass
        fun afterClass() {
            Log.d(TAG, "After Class")
        }
    }
}