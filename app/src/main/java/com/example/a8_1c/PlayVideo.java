package com.example.a8_1c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.a8_1c.data.DatabaseHelper;
import com.example.a8_1c.model.UserPlaylist;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayVideo extends AppCompatActivity {
    List<UserPlaylist> urlList = new ArrayList<>();
    DatabaseHelper db;
    String videoId;
    TextView videoTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userId",0);
        db = new DatabaseHelper(this);
        urlList = db.fetchPl(userid);

        videoId = "NcdzLLwamHU";

        videoTitle = findViewById(R.id.videoTitle);
        Button nextButton = findViewById(R.id.nextButton);
        Button prevButton = findViewById(R.id.prevButton);

        videoTitle.setText("0");
        videoPlayer(videoId);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer currentPosition = Integer.parseInt(videoTitle.getText().toString());
                if (currentPosition + 1 < urlList.size())
                {
                    videoId = urlList.get(currentPosition + 1).getUrl();
                    currentPosition = currentPosition + 1;
                    videoTitle.setText(currentPosition.toString());
                }
                else
                {
                    videoId = urlList.get(0).getUrl();
                    currentPosition = 0;
                    videoTitle.setText(currentPosition.toString());
                }


                videoPlayer(videoId);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer currentPosition = Integer.parseInt(videoTitle.getText().toString());
                if (currentPosition == 0)
                {
                    videoId = urlList.get(urlList.size() - 1).getUrl();
                    currentPosition = urlList.size() - 1;
                    videoTitle.setText(currentPosition.toString());
                }
                else
                {
                    videoId = urlList.get(currentPosition - 1).getUrl();
                    currentPosition = currentPosition - 1;
                    videoTitle.setText(currentPosition.toString());
                }

                videoPlayer(videoId);
            }
        });

    }

    public void videoPlayer(String videoId)
    {
        videoId = videoId.replace("https://www.youtube.com/watch?v=","");
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("Javascript:player.playVideo();");
            }
        }
        );

        //webView.loadUrl(urlList.get(0).getUrl());

        webView.loadData(
                "<html><body><iframe width=\"100%\" height =\"100%\" " +
                        "src=\"https://www.youtube.com/embed/" + videoId +
                        "?enablejsapi=1\" " +
                        " frameborder=\"0\" allow=”accelerometer; autoplay; " +
                        "clipboard-write; encrypted-media; gyroscope; picture-in-picture” " +
                        "allowfullscreen></iframe>" +
                        "</body></html>"
                ,"text/html"
                ,"utf-8");

        webView.getProgress();
    }


}