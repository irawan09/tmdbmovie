package irawan.electroshock.tmdbmovie.di.module;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.data.database.AppDatabase;
import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.database.retention.ApplicationContext;
import irawan.electroshock.tmdbmovie.data.database.retention.DatabaseInfo;

@Module
public class DatabaseModule {

    @ApplicationContext
    private final Context mContext;

    @DatabaseInfo
    private final String mDBName = "movies_database.db";

    public DatabaseModule (@ApplicationContext Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase () {
        return Room.databaseBuilder(
                mContext,
                AppDatabase.class,
                mDBName
        ).fallbackToDestructiveMigration().build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() { return mDBName; }

    @Singleton
    @Provides
    MoviesDao provideMovieDao(AppDatabase db) { return db.moviesDao(); }

}
