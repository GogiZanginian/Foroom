package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.pages.ChangePasswordPage

class ChangePasswordSteps {

    fun enterPassword(password: String): ChangePasswordSteps {
        with(ChangePasswordPage) {
            passwordInput.typeText(password)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun enterRepeatPassword(repeatPassword: String): ChangePasswordSteps {
        with(ChangePasswordPage) {
            repeatPasswordInput.typeText(repeatPassword)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun confirmNewPassword(): ChangePasswordSteps {
        with(ChangePasswordPage) {
            clickSaveBtn.tap()
        }
        return this
    }


}