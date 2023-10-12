package com.excavator.authomator1

import android.os.Environment
import android.util.Log
import android.view.KeyEvent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists


@RunWith(AndroidJUnit4::class)
class SberAppTest{
    private val envPath: String
    private val device: UiDevice
    private val screenshotDirName: String
    private val pathMainPassword: String
    private val pathAccountNumber: String
    private val pathAmount: String
    private val pathResult: String


    init {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
        envPath = Environment.getExternalStorageDirectory().absolutePath
        screenshotDirName = "$envPath/Pictures/Automator/Screenshots"
        pathMainPassword = "$envPath/Pictures/Automator/Data/password.txt"
        pathAccountNumber = "$envPath/Pictures/Automator/Data/account.txt"
        pathAmount = "$envPath/Pictures/Automator/Data/amount.txt"
        pathResult = "$envPath/Pictures/Automator/Data/result.txt"
    }

    @Before
    fun setUp() {
        Log.d(TAG, "Before Testt")
        // in vivo Y22 /storage/emulated/0/Pictures
        val screenshotPath = Paths.get(screenshotDirName)
        if (!screenshotPath.exists()) {
            Files.createDirectories(screenshotPath)
        }
        cleanResult()
        goHomePage()
    }
    @Test
    fun scanSberApplication() {
        try {
            clickSberIcon()
            val checkSMSPage = device.wait(Until.findObject(By.res("ru.sberbankmobile:id/start_client_button").text("Войти по номеру карты")), 100)
            if(checkSMSPage  != null){
                return
            }
            val bannerInterPassword  = device.wait(Until.findObject(By.text("Введите пароль")), 2000)
            if(bannerInterPassword != null)  {
                interPassword()
            }
            clickPayment()
            clickTransferBySber()
            setCardNumber()
            clickContinueButton()
            clickConrinueBanner()
            setAmount()
            putSuccessToResult()
        }
        catch (e: Exception){
            println("Sorry, I can't do something with it!!!!!")
        }
        finally {
            goHomePage()
            // mayby need gestue
//            goHomePage()
        }
    }

    @After
    fun after(){
        Log.d(TAG, "in Prod it go Home Page")
//        goHomePage()
    }

    // actions
    fun checkSMSPage(){
        device.wait(Until.findObject(By.res("ru.sberbankmobile:id/start_client_button").text("Войти по номеру карты")), 100)
    }
    fun goHomePage(){
        device.pressHome()
    }

    fun clickSberIcon(){
        val sber = device.wait(Until.findObject(By.text("СберБанк")), 200)
        sber.click()
    }

    fun interPassword(){
        val pass = requestMainPassword()
        for (ch in pass.iterator()) {
            val button = device.wait(Until.findObject(By.res("ru.sberbankmobile:id/keyboard_number_${ch}")), 20)
            button.click()
        }
    }

    fun clickPayment(){
        device.wait(Until.findObject(By.res("ru.sberbankmobile:id/payment_click")), 10000)
        device.wait(Until.findObject(By.desc("Платежи, вкладка, 4 из 5")), 200).click()
    }

    fun clickTransferBySber(){
        device.wait(Until.findObject(By.res("ru.sberbankmobile:id/shimmer")), 100).click()
    }

    fun setCardNumber(){
        // in file string "2202206774895416"
        val cardNumber = requestCardNumber()
        val window = device.wait(Until.findObject(By.res("ru.sberbankmobile:id/dtib_edit_text_view")), 100)
        window.setText(cardNumber)
    }

    fun clickContinueButton(){
        device.wait(Until.findObject(By.res("ru.sberbankmobile:id/continue_button")), 100).click()
    }

    fun clickConrinueBanner(){
        device.wait(Until.findObject(By.res("ru.sberbankmobile:id/action_button_view").text("Продолжить")), 1000).click()
    }

    fun setAmount(){
        // in file string "50"
        var amount = requestAmount()
        //device.pressKeyCode(KeyEvent.KEYCODE_9) // it work
        device.wait(Until.findObject(By.text("ноль рублей, ноль копеек").res("ru.sberbankmobile:id/edit_text_view")), 100)
        for (ch in amount.iterator()) {
            when(ch){
                '0' -> device.pressKeyCode(KeyEvent.KEYCODE_0)
                '1' -> device.pressKeyCode(KeyEvent.KEYCODE_1)
                '2' -> device.pressKeyCode(KeyEvent.KEYCODE_2)
                '3' -> device.pressKeyCode(KeyEvent.KEYCODE_3)
                '4' -> device.pressKeyCode(KeyEvent.KEYCODE_4)
                '5' -> device.pressKeyCode(KeyEvent.KEYCODE_5)
                '6' -> device.pressKeyCode(KeyEvent.KEYCODE_6)
                '7' -> device.pressKeyCode(KeyEvent.KEYCODE_7)
                '8' -> device.pressKeyCode(KeyEvent.KEYCODE_8)
                '9' -> device.pressKeyCode(KeyEvent.KEYCODE_9)
            }
        }
    }

    // get data


    fun getScreenshot(){
        var fileName = screenshotDirName + "/" + getRandomString(7)+ ".png"
        var file = File(fileName)
        val isNewFileCreated :Boolean = file.createNewFile()

        if(isNewFileCreated){
            device.takeScreenshot(file)
        }
    }

    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun requestMainPassword (): String {
//      // return "22551"
        val res = device.executeShellCommand("cat ${pathMainPassword}")
        Log.d(TAG, res)
        return res.trim()
    }

    fun requestCardNumber(): String {
        val res = device.executeShellCommand("cat ${pathAccountNumber}")
        Log.d(TAG, res)
        return res.trim()
    }

    fun requestAmount(): String {
        val res = device.executeShellCommand("cat ${pathAmount}")
        Log.d(TAG, res)
        return res.trim()
    }

    fun putSuccessToResult(){
        device.executeShellCommand("touch $envPath/Pictures/Automator/Data/result/success.result")
    }

    fun cleanResult(){
        device.executeShellCommand("rm $envPath/Pictures/Automator/Data/result/*.result")
    }

    companion object {
        private const val TAG = "Automator:"

        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            Log.d(TAG, "Before SberAppTest")
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            Log.d(TAG, "After SberAppTest")
        }
    }
}
