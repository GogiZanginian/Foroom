package com.example.foroom.steps

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.constants.Constants.MAX_SWIPES
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ConversationPage
import com.example.foroom.pages.ConversationPage.messagesRecyclerView

class ConversationSteps {

    fun enterMessage(message: String): ConversationSteps {
        with(ConversationPage) {
            messageEditText.typeText(message)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun sendMessage(): ConversationSteps {
        with(ConversationPage) {
            sendMessageButton.tap()
        }
        return this
    }


    fun validateChatOpened(expectedChatName: String): ConversationSteps {
        with(ChatsPage) {
            chatName.isViewDisplayed()
            chatCloseBtn.isViewDisplayed()
            onView(chatName).check(matches(withText(expectedChatName)))
            sendMessageBtn.isViewDisplayed()
        }
        return this
    }


    fun validateMessageIsDisplayed(text: String): ConversationSteps {
        with(ConversationPage) {
            findMessageByText(text).isViewDisplayed()
        }
        return this
    }


    fun sendMessages(message: String, count: Int): ConversationSteps {
        for (number in 1..count) {
            val text = "$message #$number"
            enterMessage(text)
            sendMessage()
            validateMessageIsDisplayed(text)
        }
        return this
    }

    fun validateMessageIsNotVisible(message: String): ConversationSteps {
        with(ConversationPage) {
            !findMessageByText(message).isViewDisplayed()
        }
        return this
    }

    fun scrollToOlderMessages() {

        val location = IntArray(2)
        var height = 0

        onView(messagesRecyclerView)
            .check { view, _ ->
                view.getLocationOnScreen(location)
                height = view.height
            }

        val start = location[1] + height * 40 / 100

        val end = location[1] + height * 80 / 100

        swiper(
            start = start,
            end = end,
            delay = 300
        )
    }

    fun goToMessage(targetMessage: String): ConversationSteps {
        with(ConversationPage) {
            for (n in 1..MAX_SWIPES) {
                if (findMessageByText(targetMessage).isViewDisplayed()) {
                    return this@ConversationSteps
                }
                scrollToOlderMessages()
            }
        }
        return this
    }

    fun validateMessageAndSender(message: String, sender: String): ConversationSteps {
        with(ConversationPage) {
            messageWithSender(message, sender).isViewDisplayed()
        }
        return this
    }

    fun scrollToTop() : ConversationSteps {
        with(ConversationPage) {
            onView(messagesRecyclerView)
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        }
        return this
    }

}