package com.AlkemyJava2TP3.PracticoParte3.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {
    @Bean(destroyMethod = "shutdown")
    public ExecutorService myFixedThreadPoolExecutor() {
        System.out.println("Creando ExecutorService: myFixedThreadPoolExecutor");
        return Executors.newFixedThreadPool(3);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService myCachedThreadPoolExecutor() {
        System.out.println("Creando ExecutorService: myCachedThreadPoolExecutor");
        return Executors.newCachedThreadPool();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService mySingleThreadExecutor() {
        System.out.println("Creando ExecutorService: mySingleThreadExecutor");
        return Executors.newSingleThreadExecutor();
    }

    @PreDestroy
    public void shutdownExecutors() {
        System.out.println("Iniciando apagado de todos los ExecutorServices...");
        shutdownAndAwaitTermination(myFixedThreadPoolExecutor());
        shutdownAndAwaitTermination(myCachedThreadPoolExecutor());
        shutdownAndAwaitTermination(mySingleThreadExecutor());
        System.out.println("Apagado de ExecutorServices completado.");
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool no terminó: " + pool);
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
