package com.categoryapp.ui.settings

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.categoryapp.utils.AppConstants

class SettingsViewModel : AndroidViewModel {

    private var sharedPreferences: SharedPreferences

    constructor(application: Application) : super(application) {
        // Step 1: Create or retrieve the Master Key for encryption/decryption
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // Step 2: Initialize/open an instance of EncryptedSharedPreferences
        sharedPreferences = EncryptedSharedPreferences.create(
            AppConstants.PREFERENCES_FILE_NAME,
            masterKeyAlias,
            application,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setNightMode(nightMode: Boolean) {
        // Save data to the EncryptedSharedPreferences as usual
        sharedPreferences.edit()
            .putBoolean(AppConstants.NIGHT_MODE, nightMode)
            .apply()
    }

    fun getNightMode(): Boolean {
        // Get data from the EncryptedSharedPreferences as usual
        return sharedPreferences.getBoolean(AppConstants.NIGHT_MODE, false)
    }
}
