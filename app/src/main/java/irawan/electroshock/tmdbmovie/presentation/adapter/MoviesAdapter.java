package irawan.electroshock.tmdbmovie.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.data.model.Movies;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    List<Movies> movieList;
    private Context context;

    public MoviesAdapter(List<Movies> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movieList.get(position).getTitle());

        RequestBuilder<Drawable> requestBuilder = Glide.with(holder.itemView.getContext())
                .asDrawable().sizeMultiplier(0.5f);

        Glide.with(context)
                .load(movieList.get(position).getPosterPath())
                .thumbnail(requestBuilder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.cardview_dark_background)
                .into(holder.movieImage);

        holder.movieDescription.setText(movieList.get(position).getOverview());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
