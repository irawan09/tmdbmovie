package irawan.electroshock.tmdbmovie.model;

import androidx.constraintlayout.utils.widget.MockView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

    @SerializedName("results")
    @Expose
    private List<Movies> results;

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }
}
