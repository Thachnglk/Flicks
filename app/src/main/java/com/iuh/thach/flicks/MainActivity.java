package com.iuh.thach.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.lvMovies)
    ListView lvMovie;

    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getNowPlayingMovie();

        /* SwipeRefreshLayout */
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNowPlayingMovie();
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie mm = movies.get(position);
                long movie_id = mm.getId();

                //set Source trailer to movie detail
                if(mm.getTypeLayout() == TypeLayout.POPULAR){
                    YouTubePlayerActivity.moviePlayer = mm;
                    Intent intent = new Intent(MainActivity.this, YouTubePlayerActivity.class);
                    intent.putExtra(ResourceUtil.MOVIE_ID, movie_id);
                    startActivity(intent);
                }
                else {
                    DetailActivity.movieDetail = mm;

                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(ResourceUtil.MOVIE_ID, movie_id);
                    startActivity(intent);
                }
            }
        });

    }

    private void getNowPlayingMovie(){
        Retrofit retrofit = RetrofitUtil.create();
        final MovieApi movieApi = retrofit.create(MovieApi.class);

        movieApi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                movies = response.body().getMovies();

                lvMovie.setAdapter(new MovieAdapter(MainActivity.this, movies));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}
