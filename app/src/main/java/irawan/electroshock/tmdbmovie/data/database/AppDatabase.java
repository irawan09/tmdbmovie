package irawan.electroshock.tmdbmovie.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;

@Database(entities = {Movies.class, ObservableMovies.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
