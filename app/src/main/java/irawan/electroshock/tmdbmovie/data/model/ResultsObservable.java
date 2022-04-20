package irawan.electroshock.tmdbmovie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultsObservable {

    @SerializedName("results")
    @Expose
    private ArrayList<Movies> resultsObservable;

    public ArrayList<Movies> getResultsObservable() {
        return resultsObservable;
    }

    public void setResultsObservable(ArrayList<Movies> resultsObservable) {
        this.resultsObservable = resultsObservable;
    }
}
