package com.curso.spring.dao;

import com.curso.spring.models.Role;
import java.util.List;

public interface RoleDao {

    List<Role> getAll();
    Role getRole( long id);
    Role registerRole( Role role);
    Role updateRole( Role role);
    void deleteRole( long id);

}
