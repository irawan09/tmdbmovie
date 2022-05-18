package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.databinding.ActivityMainBinding;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;
import irawan.electroshock.tmdbmovie.presentation.fragment.HomeFragment;
import irawan.electroshock.tmdbmovie.presentation.fragment.viewmodel.MoviesViewModelFactory;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    MoviesViewModelFactory mViewModelFactory;

    @Inject
    MoviesUseCaseModule moviesUseCaseModule;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((BaseApplication) getApplication()).getNetComponent().inject(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new HomeFragment());
        transaction.commit();
    }
}