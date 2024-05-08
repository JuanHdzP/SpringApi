package com.curso.spring.dao.imp;

import com.curso.spring.dao.PermisoDao;
import com.curso.spring.models.Permiso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class PermisoDaoImp implements PermisoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Permiso> getAll(){
        String hql="From Permiso as u ";
        return (List<Permiso>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Permiso getPermiso(long id) {
        return entityManager.find(Permiso.class, id);
    }

    @Transactional
    @Override
    public Permiso registerPermiso(Permiso permiso) {
        entityManager.persist(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public Permiso updatePermiso(Permiso permiso) {
        entityManager.merge(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public void deletePermiso(long id) {
        Permiso permiso = getPermiso(id);
        if (permiso != null) {
            entityManager.remove(permiso);
        }
    }
}
