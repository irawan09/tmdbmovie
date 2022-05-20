package irawan.electroshock.tmdbmovie.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;
import irawan.electroshock.tmdbmovie.databinding.MovieCardBinding;
import kotlinx.coroutines.CoroutineDispatcher;

public class MoviesPagingAdapter
        extends PagingDataAdapter<ObservableMovies, MoviesPagingAdapter.MovieViewHolder>{

    private final Context context;

    public MoviesPagingAdapter(@NonNull DiffUtil.ItemCallback<ObservableMovies> diffCallback,
                               @NonNull CoroutineDispatcher mainDispatcher,
                               Context context) {
        super(diffCallback, mainDispatcher);
        this.context = context;
    }

    public MoviesPagingAdapter(@NonNull DiffUtil.ItemCallback<ObservableMovies> diffCallback,
                               Context context) {
        super(diffCallback);
        this.context = context;
    }

    public MoviesPagingAdapter(@NonNull DiffUtil.ItemCallback<ObservableMovies> diffCallback,
                               @NonNull CoroutineDispatcher mainDispatcher,
                               @NonNull CoroutineDispatcher workerDispatcher,
                               Context context) {
        super(diffCallback, mainDispatcher, workerDispatcher);
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesPagingAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(MovieCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesPagingAdapter.MovieViewHolder holder, int position) {

        ObservableMovies currentMovie = getItem(position);
        if( currentMovie != null){
            String title = currentMovie.get(position).getTitle();
            String image = currentMovie.get(position).getPosterPath();
            String overview = currentMovie.get(position).getOverview();
            holder.binding.movieTitle.setText(title);

            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500"+image)
                    .into(holder.binding.movieImage);

            holder.binding.movieDescription.setText(overview);
        }

    }

    /* ----------------------------------------------------------- */

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        private final MovieCardBinding binding;

        public MovieViewHolder(MovieCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    /* ----------------------------------------------------------- */
}