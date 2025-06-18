package com.AlkemyJava2TP3.PracticoParte3.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DataLoaderService {

    @Async
    public CompletableFuture<String> loadUserData(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Cargando datos de usuario para: " + userId + " en el hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Usuario_Detalles_" + userId;
        });
    }

    @Async
    public CompletableFuture<String> loadOrderHistory(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Cargando historial de pedidos para: " + userId + " en el hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Pedidos_Recientes_" + userId;
        });
    }

    @Async
    public CompletableFuture<String> loadRecommendations(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Cargando recomendaciones para: " + userId + " en el hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Recomendaciones_para_" + userId;
        });
    }
}
