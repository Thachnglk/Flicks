package com.iuh.thach.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movies;

    public MovieAdapter(@NonNull Context context, List<Movie> movies){
        super(context,-1);
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        //get data view TypeLayout of item
        TypeLayout type_layout = movies.get(position).getTypeLayout();

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Fill in data
        bindViewHolder(position,viewHolder, type_layout);

        return convertView;
    }

    private void bindViewHolder(int position, ViewHolder viewHolder, TypeLayout type_layout){
        Movie movie = movies.get(position);

        if(type_layout == TypeLayout.NON_POPULAR){
            viewHolder.tvTitle.setText(movie.getTitle());
            viewHolder.tvOverView.setText(movie.getOverview());

            //xử lý Landscape và portrait
            Configuration config = getContext().getResources().getConfiguration();
            if(config.orientation == Configuration.ORIENTATION_LANDSCAPE)
            {//LANDSCAPE
                ResourceUtil.loadImage(getContext(), TAG, viewHolder.ivPoster,movie.getBackdropPath());
            }
            else
            {//PORTRAIT
                ResourceUtil.loadImage(getContext(), TAG, viewHolder.ivPoster, movie.getPosterPath());
            }

            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.tvOverView.setVisibility(View.VISIBLE);
            viewHolder.ivPoster.setVisibility(View.VISIBLE);
            viewHolder.rlBackdrop.setVisibility(View.GONE);
        }
        else {
            viewHolder.ivPoster.setVisibility(View.GONE);

            ResourceUtil.loadImage(getContext(), TAG, viewHolder.ivBackdrop, movie.getBackdropPath());
            viewHolder.rlBackdrop.setVisibility(View.VISIBLE);

            //xử lý Landscape và portrait
            Configuration config = getContext().getResources().getConfiguration();
            if(config.orientation == Configuration.ORIENTATION_LANDSCAPE)
            {//LANDSCAPE
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.5f
                );
                viewHolder.rlBackdrop.setLayoutParams(layoutParams);
                viewHolder.tvTitle.setVisibility(View.VISIBLE);
                viewHolder.tvOverView.setVisibility(View.VISIBLE);

                viewHolder.tvTitle.setText(movie.getTitle());
                viewHolder.tvOverView.setText(movie.getOverview());
            }
            else
            {//PORTRAIT
                viewHolder.tvTitle.setVisibility(View.GONE);
                viewHolder.tvOverView.setVisibility(View.GONE);
            }
        }

    }

    static class ViewHolder{
        @BindView(R.id.ivImgPoster) //@Nullable
        ImageView ivPoster;

        @BindView(R.id.tvTitle) // @Nullable
        TextView tvTitle;

        @BindView(R.id.tvDescrpt) //@Nullable
        TextView tvOverView;

        @BindView(R.id.ivFullBackdrop) //@Nullable
        ImageView ivBackdrop;

        @BindView(R.id.rlBackdrop)
        RelativeLayout rlBackdrop;

        public ViewHolder(View convertView){
            ButterKnife.bind(this, convertView);
        }
    }
}
