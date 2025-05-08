package com.thang.todolist.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/js")
public class JsController {
                        //MediaType.APPLICATION_JSON_VALUE
    @GetMapping(value = "/app.js", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAppJs() throws IOException {
        // Load app.js from src/main/resources/js/
        ClassPathResource jsFile = new ClassPathResource("js/app.js");
        String jsContent = new String(jsFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Type", "application/javascript")
                .body(jsContent);
    }
    @GetMapping(value = "/utils.js", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUtilsJs() throws IOException {
        // Load app.js from src/main/resources/js/
        ClassPathResource jsFile = new ClassPathResource("js/utils.js");
        String jsContent = new String(jsFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Type", "application/javascript")
                .body(jsContent);
    }

    @GetMapping(value = "/script.js", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getScriptJs() throws IOException {
        // Load app.js from src/main/resources/js/
        ClassPathResource jsFile = new ClassPathResource("js/script.js");
        String jsContent = new String(jsFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Type", "application/javascript")
                .body(jsContent);
    }
}