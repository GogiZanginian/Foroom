package com.example.foroom.steps

import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.pages.ChangePasswordPage

class ChangePasswordSteps {

    fun enterPassword(password: String): ChangePasswordSteps {
        with(ChangePasswordPage) {
            passwordInput.typeText(password)
        }
        return this
    }

    fun enterRepeatPassword(repeatPassword: String): ChangePasswordSteps {
        with(ChangePasswordPage) {
            repeatPasswordInput.typeText(repeatPassword)
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