package ru.itmo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TasksController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String todoList(ModelMap map) {
        return "index";
    }
}
