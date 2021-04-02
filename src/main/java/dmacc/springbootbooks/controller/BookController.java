package dmacc.springbootbooks.controller;

import dmacc.springbootbooks.beans.Book;
import dmacc.springbootbooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Rumbi
 * Date: 3/22/21
 * Time: 11:22 PM
 */
@Controller
public class BookController {

    @Autowired
    private BookRepository repo;

    @GetMapping({"/", "/viewAll"})
    public String viewAllBooks( Model model) {
        if (repo.findAll().isEmpty()) {
            return addNewBook(model);
        }
        model.addAttribute("books", repo.findAll());
        return "books";
    }

    @GetMapping("/inputBook")
    public String addNewBook(Model model) {
        Book book = new Book();
        model.addAttribute("newBook", book);
        return "input";
    }

    @PostMapping("/inputBook")
    public String addNewBook( @ModelAttribute Book book, Model model) {
    	repo.save(book);
        return viewAllBooks(model);
    }

    @GetMapping("/edit/{id}")
    public String showUpdateBook( @PathVariable("id") long id, Model model) {
        Book book = repo.findById(id).orElse(null);
        model.addAttribute("newBook", book);
        return "input";
    }

    @PostMapping("/update/{id}")
    public String reviseBook(Book book, Model model) {
        repo.save(book);
        return viewAllBooks(model);
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        repo.findById(id).ifPresent(repo :: delete);
        return viewAllBooks(model);
    }
}
