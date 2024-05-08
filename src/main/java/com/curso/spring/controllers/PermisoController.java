package com.curso.spring.controllers;

import com.curso.spring.models.Permiso;
import com.curso.spring.services.PermisoService;
import com.curso.spring.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permiso")
public class PermisoController {


    @Autowired
    PermisoService permisoService;

    //Traer todos
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Permiso> getAll(){
        return permisoService.getAll();
    }

    //Traer por id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Permiso getPermiso(@PathVariable long id){
        return permisoService.getPermiso(id);
    }

    //Registrar
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Permiso registerPermiso(@RequestBody Permiso permiso){
        return permisoService.registerPermiso(permiso);
    }

    //Modificar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Permiso updatePermiso(@RequestBody Permiso permiso){
        return permisoService.updatePermiso(permiso);
    }

    //Eliminar
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deletePermiso(@PathVariable long id){
        permisoService.deletePermiso(id);
    }
}
