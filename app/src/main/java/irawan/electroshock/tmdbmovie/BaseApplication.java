package irawan.electroshock.tmdbmovie;

import android.app.Application;

import irawan.electroshock.tmdbmovie.di.component.AppComponent;
import irawan.electroshock.tmdbmovie.di.component.DaggerAppComponent;
import irawan.electroshock.tmdbmovie.di.module.AppModule;
import irawan.electroshock.tmdbmovie.di.module.DatabaseModule;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;
import irawan.electroshock.tmdbmovie.di.module.NetworkModule;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://api.themoviedb.org/3/"))
                .databaseModule(new DatabaseModule(this))
                .moviesRepositoryModule(new MoviesRepositoryModule())
                .build();

        appComponent.inject(this);
    }

    public AppComponent getNetComponent(){
        return appComponent;
    }
}
