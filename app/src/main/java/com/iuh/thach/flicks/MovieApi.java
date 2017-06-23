package com.iuh.thach.flicks;

import com.iuh.thach.flicks.NowPlaying;
import com.iuh.thach.flicks.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;



public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> nowPlaying();

    @GET("trailers")
    Call<Trailers> youTubeTrailer();
}

