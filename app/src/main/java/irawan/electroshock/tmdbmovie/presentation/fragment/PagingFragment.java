package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.databinding.FragmentJsonBinding;
import irawan.electroshock.tmdbmovie.databinding.PagingFragmentBinding;

public class PagingFragment extends Fragment {

    private static final String TAG = "PagingFragment Data";
    private PagingFragmentBinding pagingFragment;

    public static PagingFragment newInstance() {
        return  new PagingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        pagingFragment = PagingFragmentBinding.inflate(inflater, container, false);
        return pagingFragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}