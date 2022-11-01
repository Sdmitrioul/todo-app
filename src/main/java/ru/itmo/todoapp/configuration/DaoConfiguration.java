package ru.itmo.todoapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itmo.todoapp.dao.TasksDao;
import ru.itmo.todoapp.dao.TasksInJdbsDao;
import ru.itmo.todoapp.dao.TasksInMemoryDao;

import javax.sql.DataSource;

@Configuration
public class DaoConfiguration {
    @Bean
    public TasksDao dao() {
        if ("in_memory".equals(System.getenv("DB"))) {
            return new TasksInMemoryDao();
        }
        System.out.println(System.getenv("DATABASE_USER"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todo.db");
        dataSource.setUsername(System.getenv("DATABASE_USER"));
        dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
        
        return new TasksInJdbsDao(dataSource);
    }
    
    @Bean
    public DataSource ds() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todo.db");
        dataSource.setUsername(System.getenv("DATABASE_USER"));
        dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
    
        return dataSource;
    }
}
