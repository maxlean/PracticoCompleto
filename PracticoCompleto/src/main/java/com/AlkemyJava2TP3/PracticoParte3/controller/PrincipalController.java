package com.AlkemyJava2TP3.PracticoParte3.controller;

import com.AlkemyJava2TP3.PracticoParte3.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PrincipalController {

    @Autowired
    private NotificacionService notificationService;

    @PostMapping(value = "/principal")
    public String principal() {
        return "Principal endpoint accessed successfully";
    }

    @GetMapping("/notificacion")
    public String sendSMS(@RequestParam String username, @RequestParam String phoneNumber) {
        System.out.println("Solicitud de registro de usuario recibida para: " + username + " en el hilo: " + Thread.currentThread().getName());

        // Realiza el registro del usuario (simulado)
        System.out.println("Usuario " + username + " registrado con éxito.");

        // Envía la notificación en un hilo separado
        notificationService.sendSmsNotification(phoneNumber, "¡Bienvenido a nuestra app!");

        System.out.println("Respuesta de la API enviada inmediatamente al usuario.");
        return "Usuario " + username + " registrado y notificación de bienvenida en camino.";
    }
}
