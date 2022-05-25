package irawan.electroshock.tmdbmovie.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import irawan.electroshock.tmdbmovie.BaseApplication;
import irawan.electroshock.tmdbmovie.MainActivity;
import irawan.electroshock.tmdbmovie.data.database.retention.ApplicationContext;
import irawan.electroshock.tmdbmovie.data.database.retention.DatabaseInfo;
import irawan.electroshock.tmdbmovie.di.module.AppModule;
import irawan.electroshock.tmdbmovie.di.module.DatabaseModule;
import irawan.electroshock.tmdbmovie.data.MovieDataSourceWithPaging;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;
import irawan.electroshock.tmdbmovie.di.module.NetworkModule;
import irawan.electroshock.tmdbmovie.presentation.fragment.HomeFragment;
import irawan.electroshock.tmdbmovie.presentation.fragment.PagingFragment;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        MoviesRepositoryModule.class,
        MoviesUseCaseModule.class
})
public interface AppComponent {
    void inject(BaseApplication baseApplication);
    void inject(MainActivity mainActivity);
    void inject(HomeFragment homeFragment);
    void inject(PagingFragment pagingFragment);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    @DatabaseInfo
    String getDatabaseName();

}
