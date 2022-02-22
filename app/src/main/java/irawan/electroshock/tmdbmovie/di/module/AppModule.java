package irawan.electroshock.tmdbmovie.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.data.database.retention.ApplicationContext;

@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application mApplication){
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

}
