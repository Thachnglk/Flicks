package com.iuh.thach.flicks;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class ResourceUtil {
    private static final String TAG = ResourceUtil.class.getSimpleName();

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static final String MOVIE_ID = "movie_id";

    private static List<MovieTrailer> trailers;
    private static String src_tmp;

    public static void loadImage(Context context, String TAG ,ImageView imageView, String path){
        //load image by Picasso
        /*Picasso.with(getContext())
                .load(path)
                .placeholder(R.drawable.placeholder)
                .into(imageView);*/

        //load image by Glide
        /*Glide.with(getContext())
                .load(path)
                .placeholder(R.drawable.placeholder)
                .into(imageView);*/

        //round corner
        try {
            Picasso.with(context)
                    .load(path)
                    .placeholder(R.drawable.placeholder)
                    .transform(new RoundedCornersTransformation(10,10))
                    .into(imageView);
        } catch (Exception e){
            Log.d(TAG,e.toString());
        }

    }

    public static void getTrailerMovie(final long movie_id, final YouTubePlayerFragment youTubeFragment){
        Retrofit retrofit = RetrofitUtil.create(movie_id);
        final MovieApi movieApi = retrofit.create(MovieApi.class);

        movieApi.youTubeTrailer().enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                if(response.body().getTrailersList() != null){
                    trailers = response.body().getTrailersList();

                    //get source of video to show into YOUTUBE PLAYER
                    for(int i = 0; i< trailers.size(); i++){
                        Log.d(" TRAILER LIST", trailers.get(i).getName() + " || " + trailers.get(i).getSource());
                        if(trailers.get(i).getType().equalsIgnoreCase("Trailer"))
                            src_tmp = trailers.get(i).getSource();
                    }

                    Log.w(TAG + "num:::", "1.7. SOURCE_TRAILER = "+ src_tmp);

                    youTubePlayerFragmentInitialize(youTubeFragment, src_tmp);

                }
                else {
                    Log.d(TAG + "::: response = ", "null");
                }

            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {
                Log.d(TAG + "retrofit onFailure:: ", t.toString() );
            }
        });
    }


    private static void youTubePlayerFragmentInitialize(YouTubePlayerFragment youtubeFragment, final String source){
        youtubeFragment.initialize(BuildConfig.API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(source);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

}
