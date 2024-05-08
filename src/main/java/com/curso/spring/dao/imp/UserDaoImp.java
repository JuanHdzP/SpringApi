package com.curso.spring.dao.imp;

import com.curso.spring.dao.UserDao;
import com.curso.spring.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Transactional
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<User> getAll(){
        String hql="From User as u ";
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = getUser(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User login(User dto){
        boolean isAuth = false;
        String hql = "FROM User as u WHERE u.password is not null and u.email= :email";

        List<User> result = entityManager.createQuery(hql.toString()).setParameter("email",dto.getEmail())
                .getResultList();
        if(result.size()==0) return  null;

        User user = result.get(0);
        isAuth=true;

        if(!StringUtils.isEmpty(dto.getPassword())){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            isAuth= argon2.verify(user.getPassword(),dto.getPassword());
        }

        if(isAuth){
            return user;
        }

        return null;
    }
}
