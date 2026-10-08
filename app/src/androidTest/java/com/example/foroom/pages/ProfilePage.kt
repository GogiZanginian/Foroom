package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object ProfilePage {

    val changeLanguageItem: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.changeLanguageItem))
    }

    val changePasswordItem: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.changePasswordItem))
    }

    val signOutItem: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.signOutItem))
    }

    val changeToGeoBtn: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.languageButtonGeo))
    }

    val changeToEngBtn: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.languageButtonEng))
    }

    val logoutBtn: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.signOutItem))
    }

}