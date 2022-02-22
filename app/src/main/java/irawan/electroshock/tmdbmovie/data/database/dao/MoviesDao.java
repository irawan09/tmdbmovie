package irawan.electroshock.tmdbmovie.data.database.dao;

import android.graphics.Movie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM tb_movies")
    public List<Movies> getAll();

    @Insert
    void insertAll(MovieEntity... movieEntities);

    @Delete
    void delete(MovieEntity movieEntity);

}