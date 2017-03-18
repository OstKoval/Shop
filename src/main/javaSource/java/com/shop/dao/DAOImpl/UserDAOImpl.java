package com.shop.dao.DAOImpl;

/**
 * Created by ostap on 2/23/17.
 */

import java.util.List;

import com.shop.dao.AbstractDAO;
import com.shop.dao.UserDAO;
import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("userDao")
@Transactional
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {
    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public User saveUser(User user) {
        persist(user);
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) throws InvalidEmailException {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new InvalidEmailException("User with the email (" + email + ") doesn't exist.");
        }
        getSession().delete(user);

    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<User>) criteria.list();
    }

    @Override
    public User findUserByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User updateUser(User user) {
        Query query = getSession().createSQLQuery("update user set first_name=:firstName,last_name=:lastName,password=:password,email=:email where id=:id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        return user;
    }

    @Override
    public void merge(User user) {
        super.merge(user);
    }
}



