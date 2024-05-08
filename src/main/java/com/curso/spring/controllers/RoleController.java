package com.curso.spring.controllers;

import com.curso.spring.models.Role;
import com.curso.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService RoleService;

    //Traer todos
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Role> getAll(){
        return RoleService.getAll();
    }

    //Traer por id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Role getRole(@PathVariable long id){
        return RoleService.getRole(id);
    }

    //Registrar
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Role registerRole(@RequestBody Role role){
        return RoleService.registerRole(role);
    }

    //Modificar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Role updateRole(@RequestBody Role role){
        return RoleService.updateRole(role);
    }

    //Eliminar
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteRole(@PathVariable long id){
        RoleService.deleteRole(id);
    }
}
