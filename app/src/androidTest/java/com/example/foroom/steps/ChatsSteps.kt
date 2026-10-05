package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import org.hamcrest.Matchers

class ChatsSteps {
    fun enterChatName(chatName: String): ChatsSteps {
        with(CreateChatPage) {
            chatNameInput.typeText(chatName)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun selectChatImage(index: Int): ChatsSteps {
        onView(CreateChatPage.imageChooserItem(index))
            .perform(click())

        return this
    }


    fun clickCreateChat(): ChatsSteps {
        with(CreateChatPage) {
            createChatBtn.tap()
        }
        return this
    }

    fun validateExpectedChatName(expectedChatName: String): ChatsSteps {
        with(ChatsPage) {
            onView(chatName)
                .check(matches(isDisplayed()))
                .check(matches(withText(expectedChatName.trim())))
        }
        return this
    }

    fun closeChat(): ChatsSteps {
        with(ChatsPage) {
            chatCloseBtn.tap()
        }
        return this
    }

    fun searchChat(chatName: String): ChatsSteps {
        with(ChatsPage) {
            searchChatInput.typeText(chatName)
        }
        return this
    }

    fun validateChatDisplayedInList(expectedChatName: String): ChatsSteps {
        with(ChatsPage) {
            onView(
                Matchers.allOf(
                    chatTitle,
                    withText(expectedChatName),
                    isDescendantOfA(chatsRecyclerView)
                )
            ).check(matches(isDisplayed()))

        }
        return this
    }
}