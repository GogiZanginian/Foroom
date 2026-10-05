package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object ChatsPage {
    val chatName: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.example.design_system.R.id.chatNameTextView),
        )
    }

    val chatCloseBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.closeButton)
        )
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

}