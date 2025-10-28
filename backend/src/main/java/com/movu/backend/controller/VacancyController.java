package com.movu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movu.backend.model.Vacancy;
import com.movu.backend.repository.VacancyRepository;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    @Autowired private VacancyRepository repo;

    @GetMapping
    public List<Vacancy> list() {
        return repo.findAll();
    }

    @PostMapping
    public Vacancy create(@RequestBody Vacancy v) {
        return repo.save(v);
    }

    @GetMapping("/{id}")
    public Vacancy get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
