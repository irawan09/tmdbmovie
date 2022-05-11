package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import irawan.electroshock.tmdbmovie.BaseApplication;
import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.JSONFragmentBinding;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesAdapter;

public class JSONFragment extends Fragment {

    private static final String TAG = "JSONFragment data";
    private JSONFragmentBinding jsonFragmentBinding;


    public static JSONFragment newInstance() {
        return new JSONFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        jsonFragmentBinding = JSONFragmentBinding.inflate(inflater, container, false);
        return jsonFragmentBinding.getRoot();
    }

//    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String data;
        Bundle getData = this.getArguments();
        if(getData != null){
            data = getData.getString("MoviesData");
//            jsonFragmentBinding.jsonString.setText("There are: "+data.length()+" words");
            if(!data.isEmpty()){
                Log.d(TAG, data);
                jsonFragmentBinding.jsonString.setText(getResources().getString(R.string.hello_blank_fragment));
            }
        }
    }
}