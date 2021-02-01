package test.app.activities.splash

import test.app.base.BaseViewModel
import test.app.utils.AppPreferencesHelper

class SplashViewModel : BaseViewModel<SplashNavigator?>() {
    private lateinit var appPreferencesHelper: AppPreferencesHelper
    fun GetData(appPreferencesHelper: AppPreferencesHelper) {
        this.appPreferencesHelper = appPreferencesHelper
    }
}