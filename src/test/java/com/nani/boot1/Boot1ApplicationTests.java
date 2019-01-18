package com.nani.boot1;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.nani.boot1.dao.UserDAO;
import com.nani.boot1.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Boot1ApplicationTests {

    @Autowired
    UserDAO userDAO;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("hahaha");
        int insertCount = userDAO.insertUser(user);
        System.out.println("insertCount : " + insertCount);
    }
}

