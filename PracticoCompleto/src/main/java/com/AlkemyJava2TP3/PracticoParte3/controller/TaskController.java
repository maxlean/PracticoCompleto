package com.AlkemyJava2TP3.PracticoParte3.controller;

import com.AlkemyJava2TP3.PracticoParte3.service.BackgroundTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private BackgroundTaskService backgroundTaskService;

    @GetMapping("/start-log-task")
    public String startLogTask(@RequestParam String msg) {
        backgroundTaskService.startSimpleLogTask(msg);
        return "Tarea de log iniciada en segundo plano.";
    }

    @GetMapping("/start-another-task")
    public String startAnotherTask(@RequestParam String data) {
        backgroundTaskService.startAnotherTask(data);
        return "Otra tarea iniciada en segundo plano.";
    }
}
