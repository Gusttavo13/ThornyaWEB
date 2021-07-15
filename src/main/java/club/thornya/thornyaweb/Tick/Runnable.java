package club.thornya.thornyaweb.Tick;

import club.thornya.thornyaweb.Driver.DriverSetup;
import club.thornya.thornyaweb.ThornyaWeb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runnable {

    private synchronized static void run(){
        DriverSetup.listener();
    }

    public static void init(){
        ScheduledExecutorService executoService;
        executoService = Executors.newSingleThreadScheduledExecutor();
        executoService.scheduleAtFixedRate(Runnable::run, 0, 1, TimeUnit.SECONDS);
    }

}
