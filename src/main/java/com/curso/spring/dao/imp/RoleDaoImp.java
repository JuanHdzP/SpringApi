package com.curso.spring.dao.imp;

import com.curso.spring.dao.RoleDao;
import com.curso.spring.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Role> getAll(){
        String hql="From Role as u ";
        return (List<Role>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Role getRole(long id) {
        return entityManager.find(Role.class, id);
    }

    @Transactional
    @Override
    public Role registerRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Transactional
    @Override
    public Role updateRole(Role role) {
        entityManager.merge(role);
        return role;
    }

    @Transactional
    @Override
    public void deleteRole(long id) {
        Role role = getRole(id);
        if (role != null) {
            entityManager.remove(role);
        }
    }
}
