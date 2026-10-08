package com.example.foroom.tests

import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.constants.Constants.JOHN_WEEK_CHAT
import com.example.foroom.constants.Constants.MY_CHAT
import com.example.foroom.constants.Constants.SHARED_CHAT
import com.example.foroom.constants.Constants.USER_A
import com.example.foroom.constants.Constants.USER_A_PASS
import com.example.foroom.constants.Constants.USER_B
import com.example.foroom.constants.Constants.USER_B_PASS
import com.example.foroom.pages.DashboardPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.steps.ChatsSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.DashboardSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Before

open class ConversationBaseTest {
    protected val loginSteps = LoginSteps()
    protected val registrationSteps = RegistrationSteps()
    protected val dashboardSteps = DashboardSteps()
    protected val dashboardPage = DashboardPage
    protected val chatsPage = ChatsPage
    protected val chatsSteps = ChatsSteps()
    protected val profileSteps = ProfileSteps()
    protected val conversationSteps = ConversationSteps()
    companion object {
        private var prepared = false
    }
    @Before
     fun prepareTestData() {
        if (prepared) return

        registerUser(USER_A, USER_A_PASS)
        registerUser(USER_B, USER_B_PASS)

        loginSteps.enterUsername(USER_A)
        loginSteps.enterPassword(USER_A_PASS)
        loginSteps.clickOnSignIn()
        dashboardSteps.ensureHomePageDisplayed()


        createNewChat(JOHN_WEEK_CHAT)
        createNewChat(MY_CHAT)
        createNewChat(SHARED_CHAT)

        dashboardSteps.clickOpenProfile()
        profileSteps.logout()

        prepared = true
    }

    private fun registerUser(userName: String, password: String) {
        loginSteps.enterUsername(userName)
        loginSteps.enterPassword(password)
        loginSteps.clickOnSignIn()


        if (!dashboardPage.navBar.isViewDisplayed()) {
            loginSteps.clickOnSignUp()
            registrationSteps
                .validateRegistrationScreen()
                .enterUsername(userName)
                .enterPassword(password)
                .enterRepeatPassword(password)
                .selectAvatar(1)
                .clickOnSignUp()
            dashboardSteps.ensureHomePageDisplayed()
        }

        dashboardSteps.clickOpenProfile()
        profileSteps.logout()
    }

   private fun createNewChat(chatName: String) {
        chatsSteps.searchChat(chatName)

        if (!chatsPage.chatCard(chatName).isViewDisplayed()) {
            dashboardSteps.createNewChat()
            chatsSteps
                .enterChatName(chatName)
                .selectChatImage(2)
                .clickCreateChat()
                .validateExpectedChatName(chatName)
                .closeChat()
        }
    }


}