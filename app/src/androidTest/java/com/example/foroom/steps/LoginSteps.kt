package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.pages.LoginPage
import org.junit.Assert

class LoginSteps {
    fun enterUsername(username: String): LoginSteps {
        with(LoginPage) {
            usernameInput.typeText(username)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun enterPassword(password: String): LoginSteps {
        with(LoginPage) {
            passwordInput.typeText(password)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this
    }

    fun clickOnSignIn(): LoginSteps {
        with(LoginPage) {
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
            signInBtn.tap()
        }
        return this
    }

    fun clickOnSignUp(): LoginSteps {
        with(LoginPage) {
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
            signUpBtn.tap()
        }
        return this
    }

    fun validateIncorrectUsernameMsg(): LoginSteps {
        with(LoginPage) {
            Assert.assertTrue(incorrectUserMsg.isViewDisplayed())
        }
        return this
    }

    fun validateIncorrectPasswordMsg(): LoginSteps {
        with(LoginPage) {
            Assert.assertTrue(incorrectPassMsg.isViewDisplayed())
        }
        return this
    }

    fun validateLoginScreen(): LoginSteps {
        with(LoginPage) {
            Assert.assertTrue(usernameInput.isViewDisplayed())
            Assert.assertTrue(passwordInput.isViewDisplayed())
            Assert.assertTrue(signInBtn.isViewDisplayed())
            Assert.assertTrue(signUpBtn.isViewDisplayed())
        }
        return this
    }
}