package com.curso.spring.services;

import com.curso.spring.dao.UserDao;
import com.curso.spring.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    public User getUser( long id){
        return userDao.getUser(id);
    }

    public User registerUser( User user){
        String hash = hashPassword(user.getPassword());
        user.setPassword(hash);

        return  userDao.registerUser(user);
    }

    public User updateUser( User user){
        return userDao.updateUser(user);
    }

    public void deleteUser( long id){
        userDao.deleteUser(id);
    }

    private String hashPassword(String password){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.hash(1,1024*1,1,password);
    }

    public  User login(User user){
        return userDao.login(user);
    }
}
