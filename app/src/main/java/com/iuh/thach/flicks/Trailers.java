package com.iuh.thach.flicks;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Trailers {
    @SerializedName("youtube")
    private List<MovieTrailer> trailersList;

    public List<MovieTrailer> getTrailersList(){return this.trailersList; }

}
