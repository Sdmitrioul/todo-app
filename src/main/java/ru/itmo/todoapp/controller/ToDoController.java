package ru.itmo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itmo.todoapp.dao.TasksDao;

@Controller
@RequestMapping("/")
public class ToDoController {
    private final TasksDao dao;
    
    public ToDoController(final TasksDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String todoList(ModelMap map) {
        map.addAttribute("taskLists", dao.getTodo());
        
        return "index";
    }
}
