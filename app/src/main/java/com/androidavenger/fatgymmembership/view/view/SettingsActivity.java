package com.androidavenger.fatgymmembership.view.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.presenter.MembersPresenterContract;
import com.androidavenger.fatgymmembership.presenter.SettingsActivityPresenter;

public class SettingsActivity extends AppCompatActivity implements MembersPresenterContract.SettingsActivityView {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsActivityPresenter gymManagerSettings = new SettingsActivityPresenter(this);


        EditText username= findViewById(R.id.email_change);
        String userName = username.getText().toString().trim();

        EditText newpass= findViewById(R.id.new_password);
        String newPassword= newpass.getText().toString().trim();

        EditText oldpass= findViewById(R.id.old_password);
        String oldPassword = oldpass.getText().toString().trim();

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymManagerSettings.updateLoginInfo(SettingsActivity.this, userName, oldPassword, newPassword);
                Intent backIntent = new Intent(SettingsActivity.this, MembersActivity.class);
                startActivity(backIntent);

            }
        });

        }

    @Override
    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displaySuccess(String message) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_FatGymMembership))
                .setTitle("User Settings")
                .setMessage(message)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }
}
