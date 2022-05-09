package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.databinding.ActivityMainBinding;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCase;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesFragment;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesViewModel;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesViewModelFactory;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "MainActivity";

    @Inject
    Retrofit retrofit;

    MoviesViewModel moviesViewModel;

    @Inject
    MoviesViewModelFactory mViewModelFactory;

    @Inject
    MoviesUseCase moviesUseCase;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((BaseApplication) getApplication()).getNetComponent().inject(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MoviesFragment()).commit();

    }
}