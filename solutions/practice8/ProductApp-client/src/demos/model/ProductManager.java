package demos.model;

import demos.db.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ProductManager {
    
    
    // Setup Logger
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
    // Declare remote EJB reference
    private ProductFacadeRemote productFacade;
    
    public ProductManager() {
        // Initialise remote EJB reference by making a JDNI lookup
                try {
            Context ctx = new InitialContext();
            productFacade = (ProductFacadeRemote)ctx.lookup("java:global/ProductApp/ProductApp-ejb/ProductFacade!demos.model.ProductFacadeRemote");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error initialising EJB reference", ex);
        }
    }

    public void create(Product product) {
       // invoke remote EJB create operation
       productFacade.create(product);
    }
    public void update(Product product) {
        //  invoke remote EJB update operation
        productFacade.update(product);
    }
    public void delete(Product product) {
        //  invoke remote EJB delete operation
        productFacade.delete(product);
    }
    public Product findProduct(Integer id) {
        //  invoke remote EJB findProduct operation
        return productFacade.findProduct(id);
    }
    public List<Product> findProductByName(String name) {
        //  invoke remote EJB findProductByName operation
        return productFacade.findProductByName(name);
    }
}