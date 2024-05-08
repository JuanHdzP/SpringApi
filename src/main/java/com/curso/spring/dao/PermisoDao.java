package com.curso.spring.dao;

import com.curso.spring.models.Permiso;
import java.util.List;

public interface PermisoDao {

    List<Permiso> getAll();
    Permiso getPermiso( long id);
    Permiso registerPermiso( Permiso permiso);
    Permiso updatePermiso( Permiso permiso);
    void deletePermiso( long id);

}