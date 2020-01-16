package com.categoryapp.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProviders
import com.categoryapp.R
import com.categoryapp.ui.settings.SettingsViewModel
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * Settings Activity for the app
 *
 * @author Shridutt.Kothari
 */
class SettingsActivity: AppCompatActivity() {
    private lateinit var settingViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        settingViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        val nightMode = settingViewModel.getNightMode()
        night_mode_switch.isChecked= nightMode

        night_mode_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO;
                recreate()
            }
            settingViewModel.setNightMode(isChecked)
            delegate.applyDayNight();
        };
    }
}