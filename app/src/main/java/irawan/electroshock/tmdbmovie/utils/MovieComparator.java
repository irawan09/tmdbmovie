package irawan.electroshock.tmdbmovie.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import irawan.electroshock.tmdbmovie.data.model.Movies;

public class MovieComparator extends DiffUtil.ItemCallback<Movies> {
    @Override
    public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
