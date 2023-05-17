package com.example.a8_1c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a8_1c.data.DatabaseHelper;
import com.example.a8_1c.model.User;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button signup = findViewById(R.id.signup);

        db= new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (userName.getText().toString().equals("username") ||password.getText().toString().equals("password")) {
                    Toast.makeText(MainActivity.this, "Must enter username and password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                User result = db.fetchUser(userName.getText().toString(), password.getText().toString());

                if (result.getUser_id() != 0)
                {
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    homeIntent.putExtra("userId", result.getUser_id());
                    //Integer temp = result.getUser_id();
                    //Log.d("Main Entry",temp.toString());
                    startActivity(homeIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "The user does not exist.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signupIntent = new Intent(MainActivity.this, signup.class);
                startActivity(signupIntent);
            }
        });
    }
}