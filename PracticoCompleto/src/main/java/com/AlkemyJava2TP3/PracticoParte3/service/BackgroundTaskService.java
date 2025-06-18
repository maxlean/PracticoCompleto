package com.AlkemyJava2TP3.PracticoParte3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class BackgroundTaskService {

    @Autowired
    @Qualifier("myFixedThreadPoolExecutor")
    private ExecutorService executorService;

    public void startSimpleLogTask(String message) {
        System.out.println("Solicitud para iniciar tarea de log: " + message + " en el hilo principal: " + Thread.currentThread().getName());

        executorService.execute(() -> { // Usamos una expresión lambda para Runnable
            System.out.println("Ejecutando tarea de log para: '" + message + "' en el hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simula trabajo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Tarea de log interrumpida.");
            }
            System.out.println("Tarea de log para: '" + message + "' completada.");
        });

        System.out.println("Tarea de log enviada al pool. El hilo principal continúa.");
    }

    public void startAnotherTask(String data) {
        System.out.println("Solicitud para iniciar otra tarea para: " + data + " en el hilo principal: " + Thread.currentThread().getName());

        executorService.execute(() -> {
            System.out.println("Ejecutando otra tarea para: '" + data + "' en el hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Otra tarea interrumpida.");
            }
            System.out.println("Otra tarea para: '" + data + "' completada.");
        });
    }
}
