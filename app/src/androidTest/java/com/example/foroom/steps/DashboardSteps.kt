package com.example.foroom.steps

import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.tap
import com.example.foroom.pages.DashboardPage
import org.junit.Assert

class DashboardSteps {
    fun validateSuccessfulRegistration(): DashboardSteps {
        with(DashboardPage){
            Assert.assertTrue(navBar.isViewDisplayed())
        }
        return this
    }
    fun ensureHomePageDisplayed(): DashboardSteps {
        with(DashboardPage) {
            Assert.assertTrue(navBar.isViewDisplayed())
        }
        return this
    }


    fun clickOpenProfile(): DashboardSteps {
        with(DashboardPage) {
            openProfileBtn.tap()
        }
        return this
    }

    fun createNewChat(): DashboardSteps {
        with(DashboardPage) {
            createChatItem.tap()
        }
        return this
    }
}