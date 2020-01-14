package org.javastart.homework_27.repository;

import org.javastart.homework_27.model.Category;
import org.javastart.homework_27.model.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FilmRepository {
    private Map<String, Film> database;

    public FilmRepository() {
        this.database = new HashMap<>();
    }

    public void addFilm(Film film) {
        database.put(film.getTitle(), film);
    }


    public List<Film> findAll() {
        Collection<Film> films = this.database.values();
        return new ArrayList<>(films);
    }

    public Film findByTitle(String title) {
        return this.database.get(title);
    }

    public List<Film> findByCategory(Category category) {
        Collection<Film> films = this.database.values().stream()
                .filter(film -> film.getCategory().equals(category))
                .collect(Collectors.toList());
        return new ArrayList<>(films);
    }
}
