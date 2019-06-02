package com.andrius.homestyler.ui;

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
import java.util.HashSet;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    private static final String PREF_FILE = "prefs";

    private Gson gson = new Gson();
    private SharedPreferences prefs;

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordConfirm)
    EditText etPasswordConfirm;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prefs = getSharedPreferences(PREF_FILE, MODE_PRIVATE);

        ButterKnife.bind(this);

        btnRegister.setOnClickListener(view -> register());
    }

    private void register() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirm = etPasswordConfirm.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(this, "fill all", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        Account newAccount = new Account(username, password);
        String[] jAccounts = prefs.getStringSet("accounts", Collections.emptySet()).toArray(new String[0]);
        List<String> jAccountsList = new ArrayList<>(Arrays.asList(jAccounts));
        boolean exists = false;
        for (String jAccount : jAccountsList) {
            Account account = gson.fromJson(jAccount, Account.class);
            if (account.getUsername().equals(username)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            Toast.makeText(this, "already exists", Toast.LENGTH_SHORT).show();
        } else {
            String jAccount = gson.toJson(newAccount);
            jAccountsList.add(jAccount);
            prefs.edit().putStringSet("accounts", new HashSet<>(jAccountsList)).apply();
            Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
