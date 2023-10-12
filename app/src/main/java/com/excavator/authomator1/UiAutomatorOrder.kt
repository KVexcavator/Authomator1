package com.excavator.authomator1

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths


@RunWith(AndroidJUnit4::class)
class UiAutomatorOrder {
    /**
     * Run before the method with @Test annotation
     */
    @Before
    fun before() {
//        val dir_name = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Test"
//        val path = Paths.get(dir_name)
//        if (!path.exists()) {
//            Files.createDirectories(path)
//        }
        Log.d(TAG, "Before")
    }

    /**
     * Run after the method with @Before annotation
     * and before methods with @After annotation
     */
    @Test
    fun test() {

//        val dirName = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Automator/FirstPass"
//        File(dirName).walk().forEach {
//                // /storage/emulated/0/Pictures/Automator/FirstPass
//                // /storage/emulated/0/Pictures/Automator/FirstPass/Test-folder
//            Log.d(TAG, it.name) //FirstPass
//        }
//        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        val res = device.executeShellCommand("echo 'Barbambif'")
        Log.d(TAG, "Test run and run")
    }

    fun readFile(path: String, encoding: Charset): String {
        val encoded = Files.readAllBytes(Paths.get(path))
        return String(encoded, encoding)
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