package com.example.a8_1c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class ViewPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlist);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userId",0);

        Bundle bundle = new Bundle();
        bundle.putInt("userId", userid);

        Fragment fragment = new playlist();
        fragment.setArguments(bundle);

        FragmentManager fgm = getSupportFragmentManager();
        FragmentTransaction fgt = fgm.beginTransaction();
        fgt.replace(R.id.mainFrame,fragment);
        fgt.commit();
    }
}