package ru.itmo.todoapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.itmo.todoapp.model.Task;
import ru.itmo.todoapp.model.TasksList;

import javax.sql.DataSource;
import java.util.List;

public class TasksInJdbsDao extends JdbcDaoSupport implements TasksDao {
    public TasksInJdbsDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }
    
    @Override
    public List<TasksList> doneTask(final boolean value, final int listId, final int taskId) {
        final String sql = "UPDATE tasks SET done = ? WHERE parentId = ? AND id = ?";
        getJdbcTemplate().update(sql, value, listId, taskId);
        
        return getTodo();
    }
    
    @Override
    public int addTask(Task task) {
        final String sql = "INSERT INTO tasks (name, done, description, parentId)  VALUES (?, ?, ?, ?)";
        
        return getJdbcTemplate().update(sql, task.getName(), task.isDone(), task.getDescription(), task.getParentId());
    }
    
    @Override
    public int addList(TasksList list) {
        final String sql = "INSERT INTO lists (name)  VALUES (?)";
        
        return getJdbcTemplate().update(sql, list.getName());
    }
    
    @Override
    public List<TasksList> getTodo() {
        final String sqlList = "SELECT id, name from lists";
        final String sqlTasks = "SELECT id, parentId, done, name, description FROM tasks";
        
        List<TasksList> lists = getJdbcTemplate().query(sqlList, new BeanPropertyRowMapper<>(TasksList.class));
        List<Task> tasks = getJdbcTemplate().query(sqlTasks, new BeanPropertyRowMapper<>(Task.class));
        
        tasks.forEach(task -> lists
                .stream()
                .filter(list ->
                        list.getId() == task.getParentId()
                )
                .findAny()
                .ifPresent(list -> list.addTask(task))
        );
        
        return lists;
    }
}
