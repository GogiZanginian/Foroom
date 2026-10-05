package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.tap
import com.example.foroom.constants.Constants.SIGN_OUT_ENG
import com.example.foroom.constants.Constants.SIGN_OUT_GE
import com.example.foroom.pages.ProfilePage

class ProfileSteps {

    fun clickChangePassword(): ProfileSteps {
        with(ProfilePage) {
            changePasswordItem.tap()
        }
        return this
    }

    fun clickChangeLanguage(): ProfileSteps {
        with(ProfilePage) {
            changeLanguageItem.tap()
        }
        return this
    }


    fun changeLanguageToGeo(): ProfileSteps {
        with(ProfilePage) {
            changeToGeoBtn.tap()
        }
        return this
    }

    fun changeLanguageToEng(): ProfileSteps {
        with(ProfilePage) {
            changeToEngBtn.tap()
        }
        return this
    }

    fun validateLangIsGeo(): ProfileSteps {
        with(ProfilePage) {
            onView(ProfilePage.logoutBtn)
                .check(matches(hasDescendant(withText(SIGN_OUT_GE))))
        }
        return this
    }

    fun validateLangIsEng(): ProfileSteps {
        with(ProfilePage) {
            onView(ProfilePage.logoutBtn)
                .check(matches(hasDescendant(withText(SIGN_OUT_ENG))))
        }
        return this
    }
}