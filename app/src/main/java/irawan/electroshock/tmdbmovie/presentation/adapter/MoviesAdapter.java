package irawan.electroshock.tmdbmovie.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.MovieCardBinding;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    String TAG = "MoviesAdapter";
    ArrayList<Movies> movieList;
    private final Context context;
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
        String title = movieList.get(position).getTitle();
        String image = movieList.get(position).getPosterPath();
        String overview = movieList.get(position).getOverview();
        holder.itemBinding.movieTitle.setText(title);

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500"+image)
                .into(holder.itemBinding.movieImage);

        holder.itemBinding.movieDescription.setText(overview);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /* ----------------------------------------------------------- */

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final MovieCardBinding itemBinding;

        public MovieViewHolder(MovieCardBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
    /*-------------------------------------------------------------*/

    @SuppressLint("NotifyDataSetChanged")
    public  void updateList(ArrayList<Movies> updatedList){
        movieList = updatedList;
        notifyDataSetChanged();
    }

    public Movies getMovieAt(int position){
        return movieList.get(position);
    }
}

