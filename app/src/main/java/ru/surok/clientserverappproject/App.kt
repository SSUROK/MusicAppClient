package ru.surok.clientserverappproject

import android.app.Application
import android.app.UiModeManager
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate


class App: Application() {

    var darkTheme = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences(R.string.shared_pref_name_key.toString(), MODE_PRIVATE)
        darkTheme = isDarkTheme()
        switchTheme(darkTheme)
//        forceSwitchTheme()
    }

    private fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(R.string.dark_theme_preference.toString(),
            this.resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES)
    }

    fun switchTheme(darkThemeEnabled: Boolean) {
        darkTheme = darkThemeEnabled
        sharedPreferences.edit().putBoolean(R.string.dark_theme_preference.toString(), darkThemeEnabled).apply()
        when (darkThemeEnabled){
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}