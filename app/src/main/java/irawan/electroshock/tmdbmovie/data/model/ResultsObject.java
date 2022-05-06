package irawan.electroshock.tmdbmovie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsObject {

    @SerializedName("page")
    String page;

    @SerializedName("results")
    @Expose
    private List<MoviesObject> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<MoviesObject> getResults() {
        return results;
    }

    public void setResults(List<MoviesObject> results) {
        this.results = results;
    }
}
