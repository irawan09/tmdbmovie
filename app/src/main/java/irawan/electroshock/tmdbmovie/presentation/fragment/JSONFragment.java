package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.databinding.JsonFragmentBinding;

public class JSONFragment extends Fragment {

    private static final String TAG = "JSONFragment data";
    private JsonFragmentBinding jsonFragmentBinding;


    public static JSONFragment newInstance() {
        return new JSONFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        jsonFragmentBinding = JsonFragmentBinding.inflate(inflater, container, false);
        return jsonFragmentBinding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String data;
        Bundle getData = this.getArguments();
        if(getData != null){
            data = getData.getString("MoviesData");
            if(!data.isEmpty()){
//                Log.d(TAG, data);
                jsonFragmentBinding.jsonString.setText(data);
            }
        }
    }
}