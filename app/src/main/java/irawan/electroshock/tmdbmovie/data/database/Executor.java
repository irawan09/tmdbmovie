package irawan.electroshock.tmdbmovie.data.database;

import static java.util.concurrent.Executors.newFixedThreadPool;

import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.Movies;

public class Executor {
    private static String TAG = "Executors";
    
    public static void IOThread(Runnable t){
        ExecutorService IOExecutor = Executors.newSingleThreadExecutor();
        IOExecutor.execute(t);
    }

    public static void concurrentThread(Runnable r) throws InterruptedException, ExecutionException {
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(5);
        Future<?> futures = fixedExecutor.submit(r);
        Log.i(TAG, "All Execution is done? "+futures.isDone());
    }
}
