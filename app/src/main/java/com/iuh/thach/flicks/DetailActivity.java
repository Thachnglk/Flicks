package com.iuh.thach.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    public static Movie movieDetail;

    @BindView(R.id.tvTitle_dtl)
    TextView TitleMovie;

    @BindView(R.id.tvDescrpt_dtl)
    TextView tvDescrptMovie;

    @BindView(R.id.tvReleaseDate_dtl)
    TextView tvReleaseDate;

    @BindView(R.id.ratingMovie_dtl)
    RatingBar ratingBarMovie;

    YouTubePlayerFragment youtubeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ButterKnife.bind(this);
        youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);

        final long movieID = getIntent().getLongExtra(ResourceUtil.MOVIE_ID, 0);
        Log.d(TAG, "MOVIE PARSE ID: " + movieID);

        ResourceUtil.getTrailerMovie(movieID, youtubeFragment);

        TitleMovie.setText(movieDetail.getTitle());
        tvDescrptMovie.setText(movieDetail.getOverview());
        tvReleaseDate.setText(movieDetail.getReleaseDate());
        ratingBarMovie.setRating(movieDetail.getVoteAverage());


    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
