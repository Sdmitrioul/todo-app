package ru.itmo.todoapp.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.todoapp.dao.TasksDao;

@Controller
public class TasksController {
    private final TasksDao dao;
    
    public TasksController(TasksDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String todoList(ModelMap map) {
        map.addAttribute("taskLists", dao.getTodo());
        return "index";
    }
    
    @RequestMapping(value = "/new-list", method = RequestMethod.GET)
    public String newList(ModelMap map) {
        map.addAttribute("taskLists", dao.getTodo());
        return "index";
    }
    
    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public String updateTask(@RequestParam(name = "id") int id,
                             @RequestParam(name = "parentId") int parentId,
                             @RequestParam(name = "done") boolean isDone, ModelMap map) {
        dao.doneTask(!isDone, parentId, id);
        return "redirect:index";
    }
}
