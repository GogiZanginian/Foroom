package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants.ADDITIONAL_MSG
import com.example.foroom.constants.Constants.ADDITIONAL_MSG_COUNT
import com.example.foroom.constants.Constants.GREETING
import com.example.foroom.constants.Constants.JOHN_WEEK_CHAT
import com.example.foroom.constants.Constants.MY_CHAT
import com.example.foroom.constants.Constants.REPLY
import com.example.foroom.constants.Constants.SHARED_CHAT
import com.example.foroom.constants.Constants.USER_A
import com.example.foroom.constants.Constants.USER_A_ACADEMY_QUESTION
import com.example.foroom.constants.Constants.USER_A_PASS
import com.example.foroom.constants.Constants.USER_A_DRINK_MESSAGE
import com.example.foroom.constants.Constants.USER_B
import com.example.foroom.constants.Constants.USER_B_PASS
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ConversationTests : ConversationBaseTest() {
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
    fun sendMessageInJohnWeekChat_validateMessage() {
        val message = "$USER_A_DRINK_MESSAGE ${System.currentTimeMillis()}"

        loginSteps
            .enterUsername(USER_A)
            .enterPassword(USER_A_PASS)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()

        chatsSteps
            .searchChat(JOHN_WEEK_CHAT)
            .validateChatDisplayedInList(JOHN_WEEK_CHAT)
            .openChat(JOHN_WEEK_CHAT)

        conversationSteps
            .validateChatOpened(JOHN_WEEK_CHAT)
            .enterMessage(message)
            .sendMessage()
            .validateMessageIsDisplayed(message)


        chatsSteps
            .closeChat()
            .searchChat(JOHN_WEEK_CHAT)
            .validateChatDisplayedInList(JOHN_WEEK_CHAT)
            .openChat(JOHN_WEEK_CHAT)

        conversationSteps
            .validateChatOpened(JOHN_WEEK_CHAT)
            .validateMessageIsDisplayed(message)
    }

    @Test
    fun sendAcademyQuestionInOwnChat() {
        val question = "$USER_A_ACADEMY_QUESTION ${System.currentTimeMillis()}"

        loginSteps
            .enterUsername(USER_A)
            .enterPassword(USER_A_PASS)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()

        chatsSteps
            .searchChat(MY_CHAT)
            .validateChatDisplayedInList(MY_CHAT)
            .openChat(MY_CHAT)

        conversationSteps
            .validateChatOpened(MY_CHAT)
            .enterMessage(question)
            .sendMessage()
            .validateMessageIsDisplayed(question)
    }

    @Test
    fun continueConversationWithAnotherAccount() {
        val suffix = System.currentTimeMillis().toString().takeLast(5)
        val greetingMessage = "$GREETING $suffix"
        val replyMessage = "$REPLY $suffix"

        loginSteps
            .enterUsername(USER_A)
            .enterPassword(USER_A_PASS)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()

        chatsSteps
            .searchChat(SHARED_CHAT)
            .validateChatDisplayedInList(SHARED_CHAT)
            .openChat(SHARED_CHAT)

        conversationSteps
            .validateChatOpened(SHARED_CHAT)
            .enterMessage(greetingMessage)
            .sendMessage()
            .validateMessageIsDisplayed(greetingMessage)
            .sendMessages(ADDITIONAL_MSG + suffix, ADDITIONAL_MSG_COUNT)

        chatsSteps.closeChat()

        dashboardSteps.clickOpenProfile()
        profileSteps.logout()

        loginSteps
            .enterUsername(USER_B)
            .enterPassword(USER_B_PASS)
            .clickOnSignIn()

        dashboardSteps
            .ensureHomePageDisplayed()

        chatsSteps
            .searchChat(SHARED_CHAT)
            .validateChatDisplayedInList(SHARED_CHAT)
            .openChat(SHARED_CHAT)


        conversationSteps
            .validateChatOpened(SHARED_CHAT)
            .validateMessageIsNotVisible(greetingMessage)
            .goToMessage(greetingMessage)
            .validateMessageAndSender(greetingMessage, USER_A)
            .scrollToTop()
            .enterMessage(replyMessage)
            .sendMessage()
            .validateMessageIsDisplayed(replyMessage)

        chatsSteps.closeChat()

        dashboardSteps.clickOpenProfile()
        profileSteps.logout()

        loginSteps
            .enterUsername(USER_A)
            .enterPassword(USER_A_PASS)
            .clickOnSignIn()

        dashboardSteps.ensureHomePageDisplayed()
        chatsSteps
            .searchChat(SHARED_CHAT)
            .validateChatDisplayedInList(SHARED_CHAT)
            .openChat(SHARED_CHAT)

        conversationSteps
            .validateChatOpened(SHARED_CHAT)
            .scrollToTop()
            .validateMessageAndSender(replyMessage, USER_B)
    }

}
