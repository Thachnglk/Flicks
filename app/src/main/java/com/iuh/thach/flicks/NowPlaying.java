package com.iuh.thach.flicks;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies(){ return this.movies; }
}
