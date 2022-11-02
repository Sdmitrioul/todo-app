package ru.itmo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.todoapp.dao.TasksDao;
import ru.itmo.todoapp.model.Task;

@Controller
@RequestMapping("/task")
public class TasksController {
    private final TasksDao dao;
    
    public TasksController(TasksDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateTask(@PathVariable("id") int id,
                             @RequestParam(name = "parentId") int parentId,
                             @RequestParam(name = "done") boolean isDone) {
        dao.doneTask(!isDone, parentId, id);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteTask(@PathVariable("id") int id) {
        dao.deleteTask(id);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String createTask(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("parentId", id);
        model.addAttribute("task", new Task());
        
        return "task";
    }
    
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public String createTask(@PathVariable("id") int id, @ModelAttribute("product") Task task) {
        task.setParentId(id);
        dao.addTask(task);
        
        return "redirect:/";
    }
}
