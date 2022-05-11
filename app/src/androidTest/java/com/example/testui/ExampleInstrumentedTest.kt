package com.example.testui

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import org.junit.Before
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue

private const val BASIC_SAMPLE_PACKAGE = "com.example.testui"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class ChangeTextBehaviorTest2 {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun testButton(){
        val testButton: UiObject = device.findObject(
            UiSelector().text("Increment")
        )

        if (testButton.exists() && testButton.isEnabled) {
            testButton.click()
        }
    }

    @Test
    fun testButtonAction(){
        val testButton: UiObject = device.findObject(
            UiSelector().text("Increment")
        )

        testButton.click()

        val testText1: UiObject = device.findObject(
            UiSelector().text("1")
        )

        assertTrue(testText1.exists())
    }
}