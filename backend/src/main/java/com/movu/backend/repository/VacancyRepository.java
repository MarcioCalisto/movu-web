package com.movu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movu.backend.model.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> { }
