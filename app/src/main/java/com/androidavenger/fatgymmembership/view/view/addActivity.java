package com.androidavenger.fatgymmembership.view.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.model.Member;
import com.androidavenger.fatgymmembership.presenter.AddActivityPresenter;
import com.androidavenger.fatgymmembership.presenter.MembersPresenterContract;

public class addActivity extends AppCompatActivity implements MembersPresenterContract.AddView {

    private MembersPresenterContract.AddPresenter addPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addPresenter = new AddActivityPresenter(this);

        EditText mName= findViewById(R.id.add_name);

        EditText mLastname= findViewById(R.id.add_Lastname);

        EditText mType= findViewById(R.id.add_type);

        EditText mPhone= findViewById(R.id.add_phone);

        Button addButton = findViewById(R.id.add_mem_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memberName = mName.getText().toString();
                String memberLastname = mLastname.getText().toString();
                String memberType = mType.getText().toString();
                String memberPhone = mPhone.getText().toString();

                addPresenter.addMember(memberName,memberLastname,memberPhone,memberType);
                displayToast("Member Added");
                Intent intent = new Intent(addActivity.this,MembersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(addActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displaySuccess(String message) {

        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_FatGymMembership))
                .setTitle("Add Member")
                .setMessage(message)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }

    @Override
    public void displayError(String message) {

        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_FatGymMembership))
                .setTitle("Add Member Error")
                .setMessage(message)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}