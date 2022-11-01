package ru.itmo.todoapp.dao;

import ru.itmo.todoapp.model.Task;
import ru.itmo.todoapp.model.TasksList;

import java.util.List;

public interface TasksDao {
    List<TasksList> doneTask(boolean value, int listId, int taskId);
    
    int addTask(Task task);
    
    int addList(TasksList list);
    
    int deleteTask(int id);
    
    int deleteTasksList(int id);
    
    List<TasksList> getTodo();
}
