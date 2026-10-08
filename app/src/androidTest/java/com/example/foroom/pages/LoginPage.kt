package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.design_system.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object LoginPage {
    val usernameInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.userNameInput))
        )
    }
    val passwordInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.passwordInput))
        )
    }

    val signUpBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.signUpButton),
            hasSibling(signInBtn)
        )
    }
    val signInBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.logInButton),
        )
    }

    val incorrectUserMsg: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.descriptionTextView),
            isDescendantOfA(withId(com.alternator.foroom.R.id.userNameInput))
        )
    }
    val incorrectPassMsg: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.descriptionTextView),
            isDescendantOfA(withId(com.alternator.foroom.R.id.passwordInput))
        )
    }
}