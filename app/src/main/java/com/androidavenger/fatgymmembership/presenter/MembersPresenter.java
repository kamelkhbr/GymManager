package com.androidavenger.fatgymmembership.presenter;

import android.app.Activity;

import com.androidavenger.fatgymmembership.model.Member;
import com.androidavenger.fatgymmembership.model.db.MembersDatabaseHelper;

public class MembersPresenter implements MembersPresenterContract.MembersPresenter{


    private MembersPresenterContract.MembersView membersView ;
    private MembersDatabaseHelper membersDatabaseHelper;

    public MembersPresenter(MembersPresenterContract.MembersView membersView) {
        this.membersView = membersView;
        membersDatabaseHelper = new MembersDatabaseHelper(membersView.getContext());
    }

    @Override
    public void getAllMembers(Activity activity) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    membersView.displayAllMembers(membersDatabaseHelper.getAllMembersFromDatabase());
                } catch(Exception e) {
                    e.printStackTrace();
                    membersView.displayError(e.getMessage());
                }
            }
        });

    }

    @Override
    public void insertMember(Activity activity, Member newMember) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    membersDatabaseHelper.insertMemberIntoDatabase(newMember);
                    membersView.successMessage(newMember.getMemberName() + " inserted!");
                } catch(Exception e) {
                    e.printStackTrace();
                    membersView.displayError(e.getMessage());
                }
            }
        });

    }

    @Override
    public void deleteMember(Activity activity, Member deleteMember) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    membersDatabaseHelper.deleteMemberFromDatabase(deleteMember);
                    membersView.successMessage(deleteMember.getMemberName() + " deleted!");
                } catch(Exception e) {
                    e.printStackTrace();
                    membersView.displayError(e.getMessage());
                }
            }
        });

    }
}
