package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.design_system.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object RegistrationPage {
    val repeatPassword: Matcher<View> = withId(com.alternator.foroom.R.id.repeatPasswordInput)
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


    val repeatPasswordInput: Matcher<View> by lazy {
        Matchers.allOf(
            withId(R.id.inputEditText),
            isDescendantOfA(withId(com.alternator.foroom.R.id.repeatPasswordInput))
        )
    }

    val avatarList: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.listView)
        )
    }

    val signUpBtn: Matcher<View> by lazy {
        Matchers.allOf(
            withId(com.alternator.foroom.R.id.signUpButton),
            hasSibling(repeatPassword)
        )
    }
}
