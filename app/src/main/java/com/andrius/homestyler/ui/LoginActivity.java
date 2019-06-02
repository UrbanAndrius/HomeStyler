package com.andrius.homestyler.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrius.homestyler.R;
import com.andrius.homestyler.entity.Account;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_FILE = "prefs";

    private Gson gson = new Gson();
    private SharedPreferences prefs;

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(PREF_FILE, MODE_PRIVATE);

        ButterKnife.bind(this);

        btnRegister.setOnClickListener(view ->
                startActivity(new Intent(this, RegisterActivity.class)));

        btnLogin.setOnClickListener(view -> login());
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "fill all", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] jAccounts = prefs.getStringSet("accounts", Collections.emptySet()).toArray(new String[0]);
        List<String> jAccountsList = new ArrayList<>(Arrays.asList(jAccounts));

        boolean success = false;
        for (String jAccount : jAccountsList) {
            Account account = gson.fromJson(jAccount, Account.class);
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                success = true;
                break;
            }
        }

        if (success) {
            Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
    }
}
