package com.curso.spring.controllers;

import com.curso.spring.models.User;
import com.curso.spring.services.UserService;
import com.curso.spring.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("user")
    public class UserController {

        @Autowired
        UserService userService;

        @Autowired
        private JWTUtil jwtUtil;

        //Traer todos los usuarios
        @RequestMapping(value = "/", method = RequestMethod.GET)
        List<User> getAll(){
            return userService.getAll();
        }

        //Traer usuario por id
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        User getUser(@PathVariable long id){
            return userService.getUser(id);
        }

        //Registrar usuario
        @RequestMapping(value = "/", method = RequestMethod.POST)
        User registerUser(@RequestBody User user){
            return userService.registerUser(user);
        }

        //Modificar usuario
        @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
        User updateUser(@RequestBody User user){
            return userService.updateUser(user);
        }

        //Eliminar usuario
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        void deleteUser(@PathVariable long id){
            userService.deleteUser(id);
        }

        //Login
        @RequestMapping(value = "login",method = RequestMethod.POST)
        Map<String, Object>login(@RequestBody User dto){
            User user = userService.login(dto);

            Map<String, Object> result=new HashMap<>();
            if(user!=null){
                String token = jwtUtil.create(String.valueOf(user.getId()),user.getEmail());
                result.put("token",token);
                result.put("user",user);
            }
            return result;
        }
    }
