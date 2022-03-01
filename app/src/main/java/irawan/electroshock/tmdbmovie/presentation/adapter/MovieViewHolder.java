package irawan.electroshock.tmdbmovie.presentation.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import irawan.electroshock.tmdbmovie.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    LinearLayout moviesLayout;
    ImageView movieImage;
    TextView movieTitle, movieDescription;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        moviesLayout = itemView.findViewById(R.id.card_movie);
        movieImage = itemView.findViewById(R.id.movie_image);
        movieTitle = itemView.findViewById(R.id.movie_title);
        movieDescription = itemView.findViewById(R.id.movie_description);
    }
}
