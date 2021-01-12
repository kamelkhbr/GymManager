package com.androidavenger.fatgymmembership.presenter;

import android.app.Activity;

import com.androidavenger.fatgymmembership.model.Member;
import com.androidavenger.fatgymmembership.model.db.MembersDatabaseHelper;

public class AddActivityPresenter implements MembersPresenterContract.AddPresenter {

    private MembersPresenterContract.AddView addView;
    private MembersDatabaseHelper databaseHelper;

    public AddActivityPresenter (MembersPresenterContract.AddView addView) {
        this.addView = addView;
        databaseHelper = new MembersDatabaseHelper(addView.getContext());
    }

    @Override
    public void addMember(String memberName, String memberLastName, String memberPhone, String memberType) {

        Member member = new Member(memberName,memberLastName,memberPhone,memberType);
        long result = databaseHelper.insertMemberIntoDatabase(member);
        if (result < 0 ){
            addView.displayError(" Adding new member Failed");
        } else {
            addView.displaySuccess("Member added successfully");
        }
    }
}
