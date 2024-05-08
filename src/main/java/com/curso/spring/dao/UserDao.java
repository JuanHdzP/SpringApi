package com.curso.spring.dao;

import com.curso.spring.models.User;
import java.util.List;

public interface UserDao {

        List<User> getAll();
        User getUser( long id);
        User registerUser( User user);
        User updateUser( User user);
        void deleteUser( long id);
        User login(User user);
}
