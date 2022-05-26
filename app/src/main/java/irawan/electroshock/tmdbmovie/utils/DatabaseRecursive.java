package irawan.electroshock.tmdbmovie.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;

import irawan.electroshock.tmdbmovie.data.database.Executor;

public class DatabaseRecursive extends RecursiveAction {

    private Runnable r;

    public DatabaseRecursive(Runnable r) {
        this.r = r;
    }


    @Override
    protected void compute() {
        try {
            Executor.concurrentThread(r);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
