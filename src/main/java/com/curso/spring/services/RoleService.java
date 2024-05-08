package com.curso.spring.services;

import com.curso.spring.dao.RoleDao;
import com.curso.spring.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public List<Role> getAll(){
        return roleDao.getAll();
    }

    public Role getRole( long id){
        return roleDao.getRole(id);
    }

    public Role registerRole( Role role){
        return roleDao.registerRole(role);
    }

    public Role updateRole( Role role){
        return roleDao.updateRole(role);
    }

    public void deleteRole( long id){
        roleDao.deleteRole(id);
    }
}
