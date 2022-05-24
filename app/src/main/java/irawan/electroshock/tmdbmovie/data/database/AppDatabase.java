package irawan.electroshock.tmdbmovie.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.Movies;

@Database(entities = {Movies.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
