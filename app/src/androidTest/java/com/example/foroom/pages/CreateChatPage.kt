package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.design_system.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.withIndex
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object CreateChatPage {
    val chatNameInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.chatNameInput))
        )
    }

    val chatImageChooser: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.chatImageChooser)
        )
    }

    fun imageChooserItem(index: Int): Matcher<View> {
        return withIndex(
            Matchers.allOf(
                isAssignableFrom(ImageChooserItemView::class.java),
                isDescendantOfA(chatImageChooser)
            ),
            index
        )
    }

    val createChatBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.createChatButton)
        )
    }


}