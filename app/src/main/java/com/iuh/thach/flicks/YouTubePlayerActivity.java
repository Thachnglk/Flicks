package com.iuh.thach.flicks;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YouTubePlayerActivity extends YouTubeBaseActivity {

    private static final String TAG = YouTubePlayerActivity.class.getSimpleName();

    public static Movie moviePlayer;

    YouTubePlayerFragment youtubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player);
        youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);

        final long movieID = getIntent().getLongExtra(ResourceUtil.MOVIE_ID, 0);
        Log.d(TAG, "MOVIE PARSE ID: " + movieID);

        ResourceUtil.getTrailerMovie(movieID, youtubeFragment);
    }
}
