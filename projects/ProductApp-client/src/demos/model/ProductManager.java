package demos.model;

import demos.db.Product;
import java.util.List;

public class ProductManager {
   
    // Setup Logger
    
    // Declare remote EJB reference
    
    public ProductManager() {
        // Initialise remote EJB reference by making a JDNI lookup
        
    }

    public void create(Product product) {
       // invoke remote EJB create operation
       
    }
    public void update(Product product) {
        //  invoke remote EJB update operation
        
    }
    public void delete(Product product) {
        //  invoke remote EJB delete operation

    }
    public Product findProduct(Integer id) {
        //  invoke remote EJB findProduct operation
        return null;
    }
    public List<Product> findProductByName(String name) {
        //  invoke remote EJB findProductByName operation
        return null;
    }
}