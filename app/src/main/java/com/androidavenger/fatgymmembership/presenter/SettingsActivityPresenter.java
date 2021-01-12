package com.androidavenger.fatgymmembership.presenter;

import android.content.Context;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.androidavenger.fatgymmembership.utils.constants;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SettingsActivityPresenter implements MembersPresenterContract.SettingsActivityPresenterContract {

    private final MembersPresenterContract.SettingsActivityView gymManagerSettingsView;

    public SettingsActivityPresenter(MembersPresenterContract.SettingsActivityView gymManagerSettingsView) {
        this.gymManagerSettingsView = gymManagerSettingsView;
    }

    @Override
    public void updateLoginInfo(Context context, String newUsername, String oldPassword, String newPassword) {

        EncryptedSharedPreferences sharedPreferences;

        try {
            String alias = MasterKey.DEFAULT_MASTER_KEY_ALIAS;
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                    context.getPackageName(),
                    alias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String password = sharedPreferences.getString(constants.LOGIN_PASSWORD_KEY, null);

            if(!"".equals(newUsername)) {
                if(oldPassword.equals(password)) {
                    sharedPreferences.edit().putString(constants.LOGIN_USERNAME_KEY, newUsername).apply();
                } else {
                    gymManagerSettingsView.displayToast("Make sure you enter your old password correctly.");
                }
            }

            if(!"".equals(newPassword)) {
                if(oldPassword.equals(password)) {
                    sharedPreferences.edit().putString(constants.LOGIN_PASSWORD_KEY, newPassword).apply();
                } else {
                    gymManagerSettingsView.displayToast("Make sure you enter your old password correctly.");
                }
            }

            gymManagerSettingsView.displaySuccess("User login info successfully updated!");

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

    }
}
