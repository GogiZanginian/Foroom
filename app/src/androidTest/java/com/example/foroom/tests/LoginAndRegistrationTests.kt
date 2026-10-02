package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants.INCORRECT_PASSWORD
import com.example.foroom.constants.Constants.INCORRECT_USERNAME
import com.example.foroom.constants.Constants.VALID_PASSWORD
import com.example.foroom.constants.Constants.VALID_USERNAME
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.DashboardSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext


@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule(order = 0)
    val clearSessionRule = object : ExternalResource() {
        override fun before() {
            runBlocking {
                GlobalContext.get()
                    .get<ForoomUserDataStore>()
                    .clearUserData()
            }
        }
    }


    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)
    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val dashboardSteps = DashboardSteps()

    @Test
    fun incorrectPasswordValidation_SignIn() {
        loginSteps
            .validateLoginScreen()
            .enterUsername(VALID_USERNAME)
            .enterPassword(INCORRECT_PASSWORD)
            .clickOnSignIn()
            .validateIncorrectPasswordMsg()
    }

    @Test
    fun incorrectCredentialsValidation_SignIn() {
        loginSteps
            .validateLoginScreen()
            .enterUsername(INCORRECT_USERNAME)
            .enterPassword(INCORRECT_PASSWORD)
            .clickOnSignIn()
            .validateIncorrectUsernameMsg()
            .validateIncorrectPasswordMsg()
    }

    @Test
    fun successfulRegistration() {
        val uniqueUsername = VALID_USERNAME + System.currentTimeMillis()
        loginSteps
            .validateLoginScreen()
            .clickOnSignUp()

        registrationSteps.validateRegistrationScreen()
            .enterUsername(uniqueUsername)
            .enterPassword(VALID_PASSWORD)
            .enterRepeatPassword(VALID_PASSWORD)
            .selectAvatar(1)
            .checkSelectedAvatar(1)
            .clickOnSignUp()

        dashboardSteps.validateSuccessfulRegistration()
    }
}