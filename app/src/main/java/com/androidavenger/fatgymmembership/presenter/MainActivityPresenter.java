package com.androidavenger.fatgymmembership.presenter;

import android.content.Context;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.androidavenger.fatgymmembership.utils.constants;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

public class MainActivityPresenter implements MembersPresenterContract.LoginPresenterContract {

    private MembersPresenterContract.GymMembershipLogin gymMembershipLogin;
    EncryptedSharedPreferences sharedPreferences;

    public MainActivityPresenter(MembersPresenterContract.GymMembershipLogin gymMembershipLogin) {
        this.gymMembershipLogin = gymMembershipLogin;
    }


    @Override
    public void getLoginInfo(Context context) {
        HashMap<String, String> loginInfo = new HashMap<>();

        try {
            String alias = MasterKey.DEFAULT_MASTER_KEY_ALIAS;
            sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                    context.getPackageName(),
                    alias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String username = sharedPreferences.getString(constants.LOGIN_USERNAME_KEY, null);
            String password = sharedPreferences.getString(constants.LOGIN_PASSWORD_KEY, null);
            loginInfo.put(constants.HASHMAP_USER_KEY, username);
            loginInfo.put(constants.HASHMAP_PASS_KEY, password);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        gymMembershipLogin.initializeOnClick(loginInfo);

    }

    @Override
    public void registerManager(String username, String password) {
        sharedPreferences.edit().putString(constants.LOGIN_USERNAME_KEY, username).apply();
        sharedPreferences.edit().putString(constants.LOGIN_PASSWORD_KEY, password).apply();

        gymMembershipLogin.finishLogin();
    }
}