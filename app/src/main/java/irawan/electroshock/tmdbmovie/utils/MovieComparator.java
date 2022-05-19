package irawan.electroshock.tmdbmovie.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;

public class MovieComparator extends DiffUtil.ItemCallback<ObservableMovies> {
    @Override
    public boolean areItemsTheSame(@NonNull ObservableMovies oldItem, @NonNull ObservableMovies newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ObservableMovies oldItem, @NonNull ObservableMovies newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
