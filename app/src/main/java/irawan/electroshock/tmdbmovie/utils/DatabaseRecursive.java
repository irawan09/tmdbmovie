package irawan.electroshock.tmdbmovie.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;

public class DatabaseRecursive extends RecursiveAction {

    private final Runnable r;

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
