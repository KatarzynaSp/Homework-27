package org.javastart.homework_27.controller;

import org.javastart.homework_27.model.Category;
import org.javastart.homework_27.model.Film;
import org.javastart.homework_27.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Controller
public class FilmController {
    private FilmRepository filmRepository;

    @PersistenceUnit
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

//    @GetMapping("/")
//    public String homePage(Model model, @RequestParam(required = false, name = "category") Category category) {
//        List<Film> films = filmRepository.findAll();
//        if (Objects.nonNull(category)) {
//            films = filmRepository.findByCategory(category);
//        }
//        model.addAttribute("films", films);
//        return "home";
//    }

    @GetMapping("/")
    public String addFilm(Model model) {
        model.addAttribute("film", new Film());
        return "home";
    }

    @PostMapping("/")
    public String addFilmToDatabase(Film film) {
        filmRepository.addFilm(film);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(film);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "home";
    }

    @GetMapping("/lista")
    public String getList(Model model) {
        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "list";
    }

    @GetMapping("/film")
    public String getFilmDetails(@RequestParam("tytuł") String title, Model model) {
        Film film = filmRepository.findByTitle(title);
        model.addAttribute("film", film);
        return "film";
    }

    @GetMapping("/category")
    public String getCategory(@RequestParam("category") Category category, Model model) {
        List<Film> categoryList = filmRepository.findByCategory(category);
        model.addAttribute("category", categoryList);
        return "category";
    }
}