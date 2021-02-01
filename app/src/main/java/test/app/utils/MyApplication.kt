package test.app.utils

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.firebase.analytics.FirebaseAnalytics
import test.app.R
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump


class MyApplication : MultiDexApplication()
{
    var mContext: Context? = null
    var instace: MyApplication? = null
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        instace = this
        initializeFontLibrary()
    }

    override fun getApplicationContext(): Context? {
        return this
    }
    fun getIntance(): MyApplication? {
        return instace
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun initializeFontLibrary() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("AppleSDGothicNeoR.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }

}
