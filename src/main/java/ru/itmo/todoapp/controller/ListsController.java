package ru.itmo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itmo.todoapp.dao.TasksDao;
import ru.itmo.todoapp.model.TasksList;

@Controller
@RequestMapping("/list")
public class ListsController {
    private final TasksDao dao;
    
    public ListsController(final TasksDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteList(@PathVariable("id") int id) {
        dao.deleteTasksList(id);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createList(ModelMap model) {
        model.addAttribute("list", new TasksList());
        
        return "list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createList(@ModelAttribute("list") TasksList list) {
        dao.addList(list);
        
        return "redirect:/";
    }
}
