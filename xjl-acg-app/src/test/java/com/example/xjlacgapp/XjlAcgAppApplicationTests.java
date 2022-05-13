package com.example.xjlacgapp;

import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class XjlAcgAppApplicationTests {
@Autowired
    DataSource dataSource;
    @Test
    void contextLoads()throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

}
