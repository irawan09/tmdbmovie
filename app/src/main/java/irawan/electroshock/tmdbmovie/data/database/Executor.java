package irawan.electroshock.tmdbmovie.data.database;

import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    public static void IOThread(Runnable t){
        ExecutorService IOExecutor = Executors.newSingleThreadExecutor();
        IOExecutor.execute(t);
    }
}
