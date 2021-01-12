package com.androidavenger.fatgymmembership.view.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.model.Member;
import com.androidavenger.fatgymmembership.model.db.MembersDatabaseHelper;
import com.androidavenger.fatgymmembership.presenter.MembersPresenter;
import com.androidavenger.fatgymmembership.presenter.MembersPresenterContract;
import com.androidavenger.fatgymmembership.utils.constants;
import com.androidavenger.fatgymmembership.view.view.adapter.MemberItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MembersActivity extends AppCompatActivity implements MembersPresenterContract.MembersView, MemberItemAdapter.MemberDelegate {

    private MembersPresenterContract.MembersPresenter membersPresenter;
    SharedPreferences sharedPreferences;

    private Member deleteMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        boolean dbInitialized = sharedPreferences.getBoolean(constants.DB_INITIALIZED_KEY  , false);
        membersPresenter = new MembersPresenter(this);
        membersPresenter.getAllMembers(this);

        if(!dbInitialized){
            membersPresenter.insertMember(this, new Member("Steve","Harvey","6149119111","GOLD" ));
            membersPresenter.insertMember(this, new Member("John","Cena","3401233214","SILVER" ));

            membersPresenter.getAllMembers(this);
            sharedPreferences.edit().putBoolean(constants.DB_INITIALIZED_KEY, true).apply();
        }

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MembersActivity.this, addActivity.class);
                startActivity(addIntent);
            }
        });
        ImageView btnSettings = findViewById(R.id.Setting_iv);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(MembersActivity.this, SettingsActivity.class);
                startActivity(settingIntent);
            }
        });


        Button btnDelete= findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelected(deleteMember);

            }
        });

    }

    @Override
    public void displayAllMembers(List<Member> allMembers) {
        Log.d("TAG_X", "All  Shoes: " + allMembers.size());

        ListView membersLv = findViewById(R.id.members_listview);
        ArrayList<Member> memberArrayList = new ArrayList<>(allMembers);
        MemberItemAdapter adapter = new MemberItemAdapter(memberArrayList,this);
        membersLv.setAdapter(adapter);

    }

    @Override
    public void displayError(String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog.Builder(new ContextThemeWrapper(MembersActivity.this, R.style.Theme_FatGymMembership))
                        .setTitle("Database Error")
                        .setMessage(errorMessage)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });



    }

    @Override
    public void successMessage(String successMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog.Builder(new ContextThemeWrapper(MembersActivity.this, R.style.Theme_FatGymMembership))
                        .setTitle("Database Success")
                        .setMessage(successMessage)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void selectMember(Member selectedMember) {
        deleteMember = selectedMember;

        TextView selectName = findViewById(R.id.select_name);
        String itemName = selectedMember.getMemberName();
        selectName.setText(itemName);

        TextView selectLastname = findViewById(R.id.select_lastname);
        String itemLastname = selectedMember.getMemberLastName();
        selectLastname.setText(itemLastname);

        TextView selectType = findViewById(R.id.select_type);
        String itemType = selectedMember.getMemberType();
        selectType.setText(itemType);

        TextView selectPhone = findViewById(R.id.select_phone);
        String itemPhone = selectedMember.getMemberPhone();
        selectPhone.setText(itemPhone);

    }
    public void deleteSelected (Member deleteSelected){

        membersPresenter.deleteMember(this, deleteSelected);
        membersPresenter.getAllMembers(this);


    }


}