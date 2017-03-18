package com.shop.dao.DAOImpl;

import com.shop.dao.AbstractDAO;
import com.shop.dao.RoleDAO;
import com.shop.entities.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ostap on 3/1/17.
 */
@Repository("roleDao")
@Transactional
public class RoleDAOImpl extends AbstractDAO<Integer, Role> implements RoleDAO {
    @Override
    public Role findRoleByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Role) criteria.uniqueResult();
    }

    @Override
    public void saveRole(Role role) {
        persist(role);
    }

    @Override
    public List<Role> findRolesByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return criteria.list();
    }


}
