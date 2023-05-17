package com.example.a8_1c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a8_1c.data.DatabaseHelper;
import com.example.a8_1c.model.User;

public class signup extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText sUserName = findViewById(R.id.sUserName);
        EditText sPassword = findViewById(R.id.sPassword);
        EditText scPassword = findViewById(R.id.scPassword);
        EditText sFullname = findViewById(R.id.sFullName);
        Button signUp = findViewById(R.id.signupSave);

        db = new DatabaseHelper(this);

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String fullname = sFullname.getText().toString();
                String uName = sUserName.getText().toString();
                String pw = sPassword.getText().toString();
                String scpw = scPassword.getText().toString();

            if (pw.equals(scpw))
            {
                User nUser = new User(uName, pw);
                nUser.setFull_name(fullname);
            long result = db.insertUser(nUser);
            if (result > 0)
            {
            Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
            Toast.makeText(signup.this, "Registration error", Toast.LENGTH_SHORT).show();
            }
            }
            else
            {
            Toast.makeText(signup.this, "Passwords don't match.", Toast.LENGTH_SHORT).show();
            }
            }
            }
        );
    }
}