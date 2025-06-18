package com.AlkemyJava2TP3.PracticoParte3.controller;

import com.AlkemyJava2TP3.PracticoParte3.service.DataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class DashboardController {
    @Autowired
    private DataLoaderService dataLoaderService;

    @GetMapping("/dashboard/{userId}")
    public String getUserDashboard(@PathVariable String userId) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println("Solicitud de dashboard para: " + userId + " en el hilo: " + Thread.currentThread().getName());

        // Lanza todas las tareas en paralelo
        CompletableFuture<String> userDataFuture = dataLoaderService.loadUserData(userId);
        CompletableFuture<String> orderHistoryFuture = dataLoaderService.loadOrderHistory(userId);
        CompletableFuture<String> recommendationsFuture = dataLoaderService.loadRecommendations(userId);

        // Espera a que todas las tareas completen y combina los resultados
        // 'allOf' no devuelve nada, por eso luego usamos .get() en cada Future individual
        CompletableFuture.allOf(userDataFuture, orderHistoryFuture, recommendationsFuture).join(); // .join() es como .get() pero no lanza checked exceptions

        String userData = userDataFuture.get();
        String orderHistory = orderHistoryFuture.get();
        String recommendations = recommendationsFuture.get();

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total para cargar el dashboard: " + (endTime - startTime) + "ms");

        return String.format("Dashboard para %s: %n - %s %n - %s %n - %s",
                userId, userData, orderHistory, recommendations);
    }
}
