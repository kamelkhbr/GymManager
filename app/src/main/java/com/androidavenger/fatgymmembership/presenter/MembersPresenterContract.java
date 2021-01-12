package com.androidavenger.fatgymmembership.presenter;

import android.app.Activity;
import android.content.Context;

import com.androidavenger.fatgymmembership.model.Member;

import java.util.HashMap;
import java.util.List;

public interface MembersPresenterContract {

    interface LoginPresenterContract {
        void getLoginInfo(Context context);
        void registerManager(String username, String password);
    }

    interface GymMembershipLogin {
        void initializeOnClick(HashMap<String, String> loginInfo);
        void finishLogin();
    }


    interface MembersView{
        void displayAllMembers(List<Member> allMembers);
        void displayError(String errorMessage);
        void successMessage(String successMessage);
        Context getContext();

    }

    interface MembersPresenter {
        void getAllMembers(Activity activity);
        void insertMember(Activity activity,Member newMember);
        void deleteMember(Activity activity,Member deleteMember);

    }

    interface AddPresenter {
        void addMember(String memberName,String memberLastName, String memberPhone, String memberType);
    }

    interface AddView {
        void displayToast(String message);
        void displaySuccess(String message);
        void displayError(String message);
        Context getContext();
    }

    interface SettingsActivityPresenterContract {
        void updateLoginInfo(Context context, String newUsername, String oldPassword, String newPassword);
    }

    interface SettingsActivityView {
        void displayToast(String message);
        void displaySuccess(String message);
    }


}
