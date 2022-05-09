package irawan.electroshock.tmdbmovie.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.MovieCardBinding;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    ArrayList<Movies> movieList;
    private Context context;
    MovieCardBinding binding;

    public MoviesAdapter(ArrayList<Movies> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = MovieCardBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.itemBinding.movieTitle.setText(movieList.get(position).getTitle());

        RequestBuilder<Drawable> requestBuilder = Glide.with(holder.itemView.getContext())
                .asDrawable().sizeMultiplier(0.5f);

        Glide.with(context)
                .load(movieList.get(position).getPosterPath())
                .thumbnail(requestBuilder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.cardview_dark_background)
                .into(holder.itemBinding.movieImage);

        holder.itemBinding.movieDescription.setText(movieList.get(position).getOverview());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /* ----------------------------------------------------------- */

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieCardBinding itemBinding;

        public MovieViewHolder(MovieCardBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public  void updateList(ArrayList<Movies> updatedList){
            movieList = updatedList;
            notifyDataSetChanged();
        }

        public Movies getMovieAt(int position){
            return movieList.get(position);
        }

    }
}

