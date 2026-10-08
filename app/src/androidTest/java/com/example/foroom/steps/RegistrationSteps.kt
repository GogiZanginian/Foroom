package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.typeText
import com.example.foroom.pages.RegistrationPage
import org.hamcrest.Matcher
import org.junit.Assert

class RegistrationSteps {
    fun enterUsername(username: String): RegistrationSteps {
        with(RegistrationPage) {
            usernameInput.typeText(username)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this;
    }

    fun enterPassword(password: String): RegistrationSteps {
        with(RegistrationPage) {
            passwordInput.typeText(password)
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this;
    }

    fun enterRepeatPassword(password: String): RegistrationSteps {
        with(RegistrationPage) {
            repeatPasswordInput.typeText(password)
            onView(isRoot()).perform(ViewActions.closeSoftKeyboard())
        }
        return this;
    }

    fun clickOnSignUp(): RegistrationSteps {
        with(RegistrationPage) {
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
            signUpBtn.tap()
        }
        return this;
    }

    fun validateRegistrationScreen(): RegistrationSteps {
        with(RegistrationPage) {
            Assert.assertTrue(usernameInput.isViewDisplayed())
            Assert.assertTrue(passwordInput.isViewDisplayed())
            Assert.assertTrue(repeatPasswordInput.isViewDisplayed())
            Assert.assertTrue(avatarList.isViewDisplayed())
            Assert.assertTrue(signUpBtn.isViewDisplayed())
        }
        return this
    }

    fun selectAvatar(index: Int): RegistrationSteps {
        onView(RegistrationPage.avatarList)
            .perform(selectAvatarAt(index))

        return this
    }

    private fun selectAvatarAt(index: Int): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(ImageChooserListView::class.java)
            }

            override fun getDescription(): String {
                return "Select avatar at index $index"
            }

            override fun perform(
                uiController: UiController,
                view: View
            ) {
                val avatarList = view as ImageChooserListView
                avatarList.selectImageAt(index)
                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    fun checkSelectedAvatar(index: Int): RegistrationSteps {
        onView(RegistrationPage.avatarList)
            .check { view, _ ->
                val avatarList = view as ImageChooserListView
                Assert.assertEquals(index, avatarList.selectedIndex)
            }

        return this
    }

}
