package test.app.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreferencesHelper(context: Context) {
    private val mPrefs: SharedPreferences
    fun saveStringInPreference(key: String?,value: String?) {
        mPrefs.edit().putString(key, value).apply()
    }

    fun saveIntInPreference(strKey: String?, value: Int?) {
       mPrefs.edit().putInt(strKey, value?:0).apply()
   }
    fun getStringFromPreference(key: String?): String? {
        return mPrefs.getString(key, null)
    }
    fun getIntFromPreference(key: String?): Int? {
        return mPrefs.getInt(key, 0)
    }
    fun saveBoolean(key: String?, value: Boolean?) {
        mPrefs.edit().putBoolean(key, value!!).apply()
    }
    fun getbooleanFromPreference(key: String?): Boolean? {
        return mPrefs.getBoolean(key, false)
    }
    fun cleardata() {
        mPrefs.edit().clear().apply()
    }
    init {
        mPrefs = context.getSharedPreferences("test", Context.MODE_PRIVATE)
    }
}