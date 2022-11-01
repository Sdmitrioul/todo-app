package ru.itmo.todoapp.model;

import java.util.ArrayList;
import java.util.List;

public class TasksList implements Comparable<TasksList> {
    private int id;
    private String name;
    private List<Task> tasks = new ArrayList<>();
    
    public TasksList() {
    }
    
    public TasksList(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(TasksList o) {
        return Integer.compare(this.id, o.id);
    }
}
