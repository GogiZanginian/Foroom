package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.design_system.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object ChangePasswordPage {

    val passwordInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.passwordInput))
        )
    }
    val repeatPasswordInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.repeatPasswordInput))
        )
    }

    val clickSaveBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.actionButton)
        )
    }

}