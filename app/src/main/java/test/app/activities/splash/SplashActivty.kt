package test.app.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import test.app.R
import test.app.activities.showdata.ShowdataActivity
import test.app.base.BaseActivity
import test.app.databinding.ActivitySplashBinding
import test.app.utils.AppPreferencesHelper


class SplashActivity :
    BaseActivity(),
    SplashNavigator {
    private lateinit var ActivitySplashBinding: ActivitySplashBinding
    private var splashViewModel: SplashViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(this).get<SplashViewModel>(SplashViewModel::class.java)
        ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        splashViewModel?.setNavigator(this)
        splashViewModel?.GetData(AppPreferencesHelper(this))
        Handler().postDelayed({
            val intent = Intent(this,ShowdataActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

