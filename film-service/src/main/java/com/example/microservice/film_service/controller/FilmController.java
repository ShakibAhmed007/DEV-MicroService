package com.example.microservice.film_service.controller;

import com.example.microservice.film_service.entity.Film;
import com.example.microservice.film_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@RefreshScope
public class FilmController {

    @Value("${greeting.message}")
    private String greetingMessage;

    @Autowired
    private FilmService filmService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return greetingMessage;
    }
}
