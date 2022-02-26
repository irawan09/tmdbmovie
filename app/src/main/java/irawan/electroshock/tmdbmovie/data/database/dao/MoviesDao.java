package irawan.electroshock.tmdbmovie.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM tb_movies")
    public List<MovieEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(MovieEntity... movieEntities);

    @Delete
    void delete(MovieEntity movieEntity);

}