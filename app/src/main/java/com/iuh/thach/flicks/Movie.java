package com.iuh.thach.flicks;

import com.google.gson.annotations.SerializedName;
import com.iuh.thach.flicks.TypeLayout;


/*
* vote_count: 2345,
id: 283995,
video: false,
vote_average: 7.6,
title: "Guardians of the Galaxy Vol. 2",
popularity: 28.157647,
poster_path: "/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg",
original_language: "en",
original_title: "Guardians of the Galaxy Vol. 2",
genre_ids: [
28,
12,
35,
878
],
backdrop_path: "/aJn9XeesqsrSLKcHfHP4u5985hn.jpg",
adult: false,
overview: "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
release_date: "2017-04-19"*/

public class Movie {

    private static final String POST_FIX = "https://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("release_date")
    private String releaseDate;

    private TypeLayout typeLayout;

    private String sourceTrailer;

    public long getId(){return this.id; }

    public String getTitle(){ return this.title;}

    public String getOverview(){ return this.overview;}

    public String getPosterPath(){
        return POST_FIX + this.posterPath;
    }

    public String getBackdropPath(){
        return POST_FIX + this.backdropPath;
    }

    public float getVoteAverage(){return this.voteAverage; }

    public String getPopularity(){return this.popularity; }

    public String getReleaseDate(){return this.releaseDate; }

    public TypeLayout getTypeLayout(){
        if(this.voteAverage > 5)
            this.typeLayout = TypeLayout.POPULAR;
        else
            this.typeLayout = TypeLayout.NON_POPULAR;
        return this.typeLayout;
    }

    public String getSourceTrailer(){ return this.sourceTrailer; }

    public void setSourceTrailer(String src){
        this.sourceTrailer = src;
    }

}
