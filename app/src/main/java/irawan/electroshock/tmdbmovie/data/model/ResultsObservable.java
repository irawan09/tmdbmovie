package irawan.electroshock.tmdbmovie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultsObservable {

    @SerializedName("results")
    @Expose
    private ArrayList<ObservableMovies> resultsObservable;

    public ArrayList<ObservableMovies> getResultsObservable() {
        return resultsObservable;
    }

    public void setResultsObservable(ArrayList<ObservableMovies> resultsObservable) {
        this.resultsObservable = resultsObservable;
    }
}
