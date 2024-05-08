package com.curso.spring.services;

import com.curso.spring.dao.PermisoDao;
import com.curso.spring.models.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    @Autowired
    PermisoDao permisoDao;

    public List<Permiso> getAll(){
        return permisoDao.getAll();
    }

    public Permiso getPermiso( long id){
        return permisoDao.getPermiso(id);
    }

    public Permiso registerPermiso( Permiso permiso){
        return permisoDao.registerPermiso(permiso);
    }

    public Permiso updatePermiso( Permiso permiso){
        return permisoDao.updatePermiso(permiso);
    }

    public void deletePermiso( long id){
        permisoDao.deletePermiso(id);
    }
}
