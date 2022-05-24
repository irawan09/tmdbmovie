package irawan.electroshock.tmdbmovie.data.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;

public class Executor {

    public static void IOThread(Runnable t){
        ExecutorService IOExecutor = Executors.newSingleThreadExecutor();
        IOExecutor.execute(t);
    }

    public static void FixedThreadPool(Runnable r){
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(10);
        fixedExecutor.execute(r);
    }
}
