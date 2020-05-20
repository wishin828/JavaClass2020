/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productclient;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import productclient.Products;
/**
 *
 * @author Admin
 */
public class ProductManager {
    @PersistenceContext(unitName="ProductClientPU")
    private EntityManager em;
    private Query ProductNameQuery;
    public ProductManager(String persistenceUnit){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();
        ProductNameQuery = em.createNamedQuery("Products.findByName");
    }

    ProductManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void closeEntityManager(){
        em.close();
    }
    public void create(@Valid Products product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }
    public void update(@Valid Products product){
        em.getTransaction().begin();
        product=em.merge(product);
        em.getTransaction().commit();
    }
    public void delete(@Valid Products product){
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }
    public Products findProduct(Integer id){
        return em.find(Products.class,id);
    }
    
    public List<Products> findProductByName(String Name){
        ProductNameQuery.setParameter("name", Name);
        return ProductNameQuery.getResultList();
    }
}
