package com.example.projectakhiraristriono;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        buttonlogin = findViewById(R.id.buttonLogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user, pswd;
                user = username.getText().toString();
                pswd = password.getText().toString();


                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                String isianUser = SP.getString("user", "admin");

                String isianPswd = SP.getString("password", "admin");

                if (user.equals(isianUser) && pswd.equals(isianPswd)) {

                    Intent login = new Intent(Login.this, Hasil.class);

                    Bundle data = new Bundle();
                    data.putString("USER", user);
                    login.putExtras(data);

                    startActivity(login);

                } else {
                    Toast.makeText(Login.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}