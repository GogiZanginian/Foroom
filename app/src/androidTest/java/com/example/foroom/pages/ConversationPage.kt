package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object ConversationPage {
    val messagesRecyclerView: Matcher<View> by lazy {
        withId(R.id.messagesRecyclerView)
    }

    val messageInput: Matcher<View> by lazy {
        withId(R.id.messageInput)
    }

    val messageEditText: Matcher<View> by lazy {
        allOf(
            withId(com.example.design_system.R.id.inputEditText),
            isDescendantOfA(messageInput)
        )
    }

    val sendMessageButton: Matcher<View>by lazy {
        withId(R.id.sendMessageButton)
    }


    fun findMessageByText(text: String): Matcher<View> =
        allOf(
            withId(com.example.design_system.R.id.messageTextView),
            withText(text),
            isDescendantOfA(messagesRecyclerView)
        )

    fun messageWithSender(text: String, senderName: String): Matcher<View> =
        allOf(
            findMessageByText(text),
            hasDescendant(
                allOf(
                    withId(com.example.design_system.R.id.userNameTextView),
                    withText(senderName)
                )
            )
        )
}