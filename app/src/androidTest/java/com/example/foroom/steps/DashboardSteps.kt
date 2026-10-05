package com.example.foroom.steps

import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.pages.DashboardPage
import org.junit.Assert

class DashboardSteps {
    fun validateSuccessfulRegistration(): DashboardSteps {
        with(DashboardPage){
            Assert.assertTrue(navBar.isViewDisplayed())
        }
        return this
    }
}