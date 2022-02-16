package irawan.electroshock.tmdbmovie.di.component;

import javax.inject.Singleton;

import dagger.Component;
import irawan.electroshock.tmdbmovie.MainActivity;
import irawan.electroshock.tmdbmovie.di.module.AppModule;
import irawan.electroshock.tmdbmovie.di.module.NetworkModule;
import irawan.electroshock.tmdbmovie.repository.RepositoryModule;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
