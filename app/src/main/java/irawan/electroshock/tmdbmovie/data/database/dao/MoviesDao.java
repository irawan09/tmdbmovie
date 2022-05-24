package irawan.electroshock.tmdbmovie.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Single;
import irawan.electroshock.tmdbmovie.data.model.Movies;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM tb_movies")
    public List<Movies> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movies... movieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObjectsAll(Single<Object>... movieEntities);

    @Delete
    void delete(Movies movieEntity);

}