package irawan.electroshock.tmdbmovie.utils;

import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    private static final String TAG = "Executors";
    
    public static void IOThread(Runnable t){
        ExecutorService IOExecutor = Executors.newSingleThreadExecutor();
        IOExecutor.execute(t);
        IOExecutor.shutdown();
        Log.i(TAG, "All Execution is done? "+IOExecutor.isShutdown());
    }

    public static void concurrentThread(Runnable r) throws InterruptedException, ExecutionException {
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(5);
        fixedExecutor.submit(r);
        fixedExecutor.shutdown();
        Log.i(TAG, "All Execution is done? "+fixedExecutor.isShutdown());
    }
}
