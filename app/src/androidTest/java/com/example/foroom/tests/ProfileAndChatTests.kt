package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants.FULLNAME
import com.example.foroom.constants.Constants.NEW_PASSWORD
import com.example.foroom.constants.Constants.PASSWORD
import com.example.foroom.constants.Constants.USERNAME
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChangePasswordSteps
import com.example.foroom.steps.ChatsSteps
import com.example.foroom.steps.DashboardSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {
    private val loginSteps = LoginSteps()
    private val dashboardSteps = DashboardSteps()
    private val profileSteps = ProfileSteps()
    private val changePasswordSteps = ChangePasswordSteps()
    private val chatsSteps = ChatsSteps()

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

    @Test
    fun changePasswordAndValidateLogin() {
        loginSteps
            .validateLoginScreen()
            .enterUsername(USERNAME)
            .enterPassword(PASSWORD)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()
            .clickOpenProfile()

        profileSteps
            .clickChangePassword()

        changePasswordSteps
            .enterPassword(NEW_PASSWORD)
            .enterRepeatPassword(NEW_PASSWORD)
            .confirmNewPassword()

        loginSteps
            .validateLoginScreen()
            .enterUsername(USERNAME)
            .enterPassword(NEW_PASSWORD)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()

        // return old password
        dashboardSteps
            .clickOpenProfile()

        profileSteps
            .clickChangePassword()

        changePasswordSteps
            .enterPassword(PASSWORD)
            .enterRepeatPassword(PASSWORD)
            .confirmNewPassword()

        loginSteps
            .validateLoginScreen()

    }

    @Test
    fun changeLanguageTest() {
    loginSteps
        .validateLoginScreen()
        .enterUsername(USERNAME)
        .enterPassword(PASSWORD)
        .clickOnSignIn()

        dashboardSteps
        .ensureHomePageDisplayed()
        .clickOpenProfile()

        profileSteps
            // -> Geo test
            .clickChangeLanguage()
            .changeLanguageToGeo()
            .validateLangIsGeo()

            // -> Eng test
            .clickChangeLanguage()
            .changeLanguageToEng()
            .validateLangIsEng()

            // -> again geo test
            .clickChangeLanguage()
            .changeLanguageToGeo()
            .validateLangIsGeo()
    }

    @Test
    fun chatsTest() {
        val generated = System.currentTimeMillis().toString().takeLast(5)
        val uniqueName = FULLNAME  + generated
        loginSteps
            .validateLoginScreen()
            .enterUsername(USERNAME)
            .enterPassword(PASSWORD)
            .clickOnSignIn()
        dashboardSteps
            .ensureHomePageDisplayed()
            .createNewChat()

        chatsSteps
            .enterChatName(uniqueName)
            .selectChatImage(2)
            .clickCreateChat()
            .validateExpectedChatName(uniqueName)
            .closeChat()
            .searchChat(uniqueName)
            .validateChatDisplayedInList(uniqueName)
    }

}