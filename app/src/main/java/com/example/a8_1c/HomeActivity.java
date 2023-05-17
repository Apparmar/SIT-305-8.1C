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
import com.example.a8_1c.model.UserPlaylist;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EditText urlText = findViewById(R.id.urlText);
        Button playButton = findViewById(R.id.playButton);
        Button addtoPlaylist = findViewById(R.id.addToPlaylist);
        Button viewPlaylist = findViewById(R.id.myPlaylist);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userId",0);
        //Log.d("First Entry", userid.toString());
        db = new DatabaseHelper(this);

        // Add new URL to playlist
        addtoPlaylist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String urlStr = urlText.getText().toString();
                //Log.d("URL ORG:" , urlStr);
                if (!urlStr.equals(""))
                {
                    long result = db.insertPl(userid, urlStr);
                    if (result > 0)
                    {
                        Toast.makeText(HomeActivity.this, "URL added to playlist", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(HomeActivity.this, "Error in adding URL to playlist", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(HomeActivity.this, "Empty URL.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // View playlist
        viewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPlaylist = new Intent(HomeActivity.this, ViewPlaylist.class);
                viewPlaylist.putExtra("userId", userid);
                startActivity(viewPlaylist);
            }
        });

        // Play
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPlaylist = new Intent(HomeActivity.this, PlayVideo.class);
                viewPlaylist.putExtra("userId", userid);
                startActivity(viewPlaylist);
            }
        });
    }
}