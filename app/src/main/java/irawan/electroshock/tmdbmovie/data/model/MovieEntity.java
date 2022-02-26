package irawan.electroshock.tmdbmovie.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Entity(tableName = "tb_movies")
public class MovieEntity implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "overview")
    @Expose
    public String overview;

    @ColumnInfo(name = "poster_path")
    @Expose
    public String posterPath;

    @ColumnInfo(name = "release_date")
    @Expose
    public String releaseDate;

    @ColumnInfo(name = "title")
    @Expose
    public String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
