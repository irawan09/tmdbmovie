package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import irawan.electroshock.tmdbmovie.databinding.ActivityMainBinding;
import irawan.electroshock.tmdbmovie.presentation.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

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