package ru.itmo.todoapp.configuration;

import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotenv = Dotenv.load();
        if ("in_memory".equals(dotenv.get("DB"))) {
            return new TasksInMemoryDao();
        }
        
        return new TasksInJdbsDao(ds());
    }
    
    @Bean
    public DataSource ds() {
        Dotenv dotenv = Dotenv.load();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todo.db");
        dataSource.setUsername(dotenv.get("DATABASE_USER"));
        dataSource.setPassword(dotenv.get("DATABASE_PASSWORD"));
    
        return dataSource;
    }
}
