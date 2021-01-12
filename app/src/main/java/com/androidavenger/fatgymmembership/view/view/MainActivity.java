package com.androidavenger.fatgymmembership.view.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.presenter.MainActivityPresenter;
import com.androidavenger.fatgymmembership.presenter.MembersPresenterContract;
import com.androidavenger.fatgymmembership.utils.constants;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MembersPresenterContract.GymMembershipLogin {

    MainActivityPresenter loginPresenter = new MainActivityPresenter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPresenter.getLoginInfo(this);
    }


    @Override
    public void initializeOnClick(HashMap<String, String> loginInfo) {

        EditText userView;
        EditText passView;
        EditText etRegisterUsername;
        EditText etRegisterPassword;
        EditText etRegisterConfirmPassword;


        Button loginButton;
        Button registerButton;

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.bt_signup);
        userView = findViewById(R.id.etUsername);
        passView = findViewById(R.id.etPassword);

        etRegisterUsername = findViewById(R.id.signup_name);
        etRegisterPassword = findViewById(R.id.signup_pass1);
        etRegisterConfirmPassword = findViewById(R.id.signup_pass2);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userView.getText().toString();
                String password = passView.getText().toString();

                if (verifyLogin(loginInfo, username, password)) {
                    Intent mainIntent = new Intent(MainActivity.this, MembersActivity.class);
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(MainActivity.this,
                            "User not found. Please try again.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etRegisterUsername.getText().toString().trim();
                String password = etRegisterPassword.getText().toString();
                String confirmPass = etRegisterConfirmPassword.getText().toString();

                if(username.equals("")) {
                    Toast.makeText(MainActivity.this,
                            "Please enter a valid username.",
                            Toast.LENGTH_LONG).show();
                } else if(password.equals("")) {
                    Toast.makeText(MainActivity.this,
                            "Please enter a valid password.",
                            Toast.LENGTH_LONG).show();
                } else if(!password.equals(confirmPass)) {
                    Toast.makeText(MainActivity.this,
                            "Make sure passwords are the same.",
                            Toast.LENGTH_LONG).show();
                } else {
                    loginPresenter.registerManager(username, password);
                }

            }

        });
    }
    public void finishLogin() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private boolean verifyLogin(HashMap<String, String> loginInfo, String username, String password) {
        return username.equals(loginInfo.get(constants.HASHMAP_USER_KEY)) &&
                password.equals(loginInfo.get(constants.HASHMAP_PASS_KEY));
    }
}