package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.tap
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

object ChatsPage {
    val chatName: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.example.design_system.R.id.chatNameTextView),
            isDescendantOfA(chatHeader)
        )
    }

    val chatCloseBtn: Matcher<View> by lazy {
        withId(com.alternator.foroom.R.id.closeButton)
    }


    val searchChatInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.example.design_system.R.id.inputEditText),
            isDescendantOfA(
                withId(com.alternator.foroom.R.id.searchChatInput)
            )
        )
    }

    val chatsRecyclerView: Matcher<View> by lazy {
        withId(com.alternator.foroom.R.id.chatsRecyclerView)
    }

    val chatTitle: Matcher<View> by lazy {
        withId(com.example.design_system.R.id.chatTitleTextView)
    }

    fun chatCard(chatName: String): Matcher<View> =
        allOf(
            withId(com.example.design_system.R.id.chatTitleTextView),
            withText(chatName),
            isDescendantOfA(chatsRecyclerView)
        )

    fun openChatButton(chatName: String): Matcher<View> =
        allOf(
            withId(com.example.design_system.R.id.sendMessageButton),
            hasSibling(chatCard(chatName))
        )

    fun clickOpenChat(chatName: String) {
        onView(openChatButton(chatName)).tap()
    }

    private val messageInput: Matcher<View> = withId(com.alternator.foroom.R.id.messageInput);

    val chatHeader: Matcher<View> by lazy {
        allOf(
            withId(com.alternator.foroom.R.id.chatHeaderView),
            hasSibling(messageInput)
        )
    }

    val sendMessageBtn: Matcher<View> by lazy {
        withId(com.alternator.foroom.R.id.sendMessageButton)
    }



}