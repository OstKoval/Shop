package com.shop.dao.DAOImpl;

import com.shop.dao.AbstractDAO;
import com.shop.dao.ProductDAO;
import com.shop.dto.validation.exception.InvalidProductName;
import com.shop.entities.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ostap on 2/25/17.
 */
@Repository("productDao")
@Transactional
public class ProductDAOImpl extends AbstractDAO<Integer, Product> implements ProductDAO {
    @Override
    public Product findProduct(int id) {
        return getByKey(id);
    }

    @Override
    public Product findProduct(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Product) criteria.uniqueResult();
    }

    @Override
    public List<Product> findProductsByCategoryAndType(String category, String type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("category", category))
                .add(Restrictions.eq("type", type));
        return criteria.list();
    }

    @Override
    public List<Product> allProducts() {
        Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list();
    }

    @Override
    public void saveProduct(Product product) {
        persist(product);
    }

    @Override
    public void deleteProduct(String name) throws InvalidProductName {
        Product product = findProduct(name);
        if (product == null)
            throw new InvalidProductName("Product " + name + "doesn't exist.");
        getSession().delete(product);
    }

    @Override
    public Product increaseQuantity(String name, int increaseValue) {
        Product increasedProduct = findProduct(name);
        Query query = getSession().createSQLQuery("UPDATE product SET quantity=:quantity WHERE id=:id");
        query.setParameter("quantity", increasedProduct.getQuantity() + increaseValue);
        query.setParameter("id", increasedProduct.getId());
        query.executeUpdate();
        return increasedProduct;
    }

    @Override
    public Product updateProduct(Product product) {

        Query query = getSession().createSQLQuery("update product set name=:name,category=:category," +
                "type=:type,price=:price,quantity=:quantity," +
                "manufacturer=:manufacturer,image=:image where id=:id");
        query.setParameter("name", product.getName());
        query.setParameter("category", product.getCategory());
        query.setParameter("type", product.getType());
        query.setParameter("price", product.getPrice());
        query.setParameter("id", product.getId());
        query.setParameter("quantity", product.getQuantity());
        query.setParameter("manufacturer", product.getManufacturer());
        query.setParameter("image", product.getImage());
        query.executeUpdate();
        return product;
    }

    @Override
    public Session getSession() {
        return super.getSession();
    }

    @Override
    public List<Product> searchByName(String name) {
        Criteria criteria = createEntityCriteria();
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("category", name, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("type", name, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("manufacturer", name, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        return criteria.list();
    }

}
