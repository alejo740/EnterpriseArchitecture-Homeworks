package cs544.exercise19;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class BookController {

    @Resource
    private IBookDao bookDao;

    @RequestMapping("/")
    public String redirectRoot() {
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "/bookStore/bookList";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public String add() {
        return "/bookStore/bookDetail";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String add(Book book) {
        bookDao.add(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDao.get(id));
        return "/bookStore/bookDetail";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable int id, Book book) {
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public String delete(int bookId) {
        bookDao.delete(bookId);
        return "redirect:/books";
    }
}
