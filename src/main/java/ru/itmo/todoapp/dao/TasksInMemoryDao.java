package ru.itmo.todoapp.dao;

import ru.itmo.todoapp.model.Task;
import ru.itmo.todoapp.model.TasksList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TasksInMemoryDao implements TasksDao {
    private final AtomicInteger lastTaskId = new AtomicInteger(-1);
    private final AtomicInteger lastListId = new AtomicInteger(-1);
    private final List<TasksList> dao = new ArrayList<>();
    
    @Override
    public List<TasksList> doneTask(final boolean value, final int listId, final int taskId) {
        dao.forEach(list -> {
            if (list.getId() == listId) {
                list.getTasks()
                        .forEach(task -> {
                            if (task.getId() == taskId) {
                                task.setDone(value);
                            }
                        });
            }
        });
        
        return List.copyOf(dao);
    }
    
    @Override
    public int addTask(Task task) {
        final List<TasksList> parent = dao
                .stream()
                .filter(element -> element.getId() == task.getParentId())
                .toList();
        if (parent.size() == 0) {
            return -1;
        }
        
        int id = lastTaskId.getAndIncrement();
        
        task.setId(id);
        
        parent.get(0)
                .addTask(task);
        
        return 0;
    }
    
    @Override
    public int addList(TasksList list) {
        int id = lastListId.incrementAndGet();
        
        list.setId(id);
        
        dao.add(list);
        
        return 0;
    }
    
    @Override
    public int deleteTask(final int id) {
        dao.forEach(it -> it.getTasks()
                .removeIf(task -> task.getId() == id));
        return 0;
    }
    
    @Override
    public int deleteTasksList(final int id) {
        dao.removeIf(it -> it.getId() == id);
        return 0;
    }
    
    @Override
    public List<TasksList> getTodo() {
        return List.copyOf(dao);
    }
}
