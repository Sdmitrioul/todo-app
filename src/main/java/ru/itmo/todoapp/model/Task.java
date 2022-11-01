package ru.itmo.todoapp.model;

public class Task implements Comparable<Task> {
    private int id;
    private int parentId;
    private boolean isDone;
    private String name;
    private String description;
    
    public Task(int id, int parentId, String name, String description) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
    }
    
    public Task() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getParentId() {
        return parentId;
    }
    
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void setDone(boolean done) {
        isDone = done;
    }
    
    public void setDone(int done) {
        isDone = done == 1;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.id, o.id);
    }
}
