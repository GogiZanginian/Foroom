package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object DashboardPage {
    val navBar: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.navBar))
    }

    val openProfileBtn: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.homeNavigationProfile))
    }

    val createChatItem: Matcher<View> by lazy {
        Matchers.allOf(withId(com.alternator.foroom.R.id.homeNavigationCreateChat))
    }

}