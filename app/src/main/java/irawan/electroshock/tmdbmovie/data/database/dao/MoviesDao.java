package irawan.electroshock.tmdbmovie.data.database.dao;

import android.graphics.Movie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Observable;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM tb_movies")
    public List<Movies> getAll();

    @Query("SELECT * FROM tb_movies")
    public List<ObservableMovies> getAllObservableMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movies... movieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllObservable(ObservableMovies... movieEntities);

    @Delete
    void delete(Movies movieEntity);

    @Delete
    void delete(ObservableMovies movieEntities);

}