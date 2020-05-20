package demos.model;

import demos.db.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ProductManager {
   
    // Setup Logger
    private static final Logger logger =Logger.getLogger(ProductManager.class.getName());
    private ProductFacadeRemote productFacade;
    // Declare remote EJB reference
    
    public ProductManager() {
        try {
            Context ctx = new InitialContext();
            productFacade = (ProductFacadeRemote)ctx.lookup(
                    "java:global/ProductApp/ProductApp-ejb/ProductFacade!demos.model.ProductFacadeRemote");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error initialising EJB reference", ex);
        }
    }

    public void create(Product product) {
        productFacade.create(product);
    }
    public void update(Product product) {
        productFacade.update(product);
    }
    public void delete(Product product) {
        productFacade.delete(product);
    }
    public Product findProduct(Integer id) {
        Product p=productFacade.findProduct( id);
        return p;
    }
    public List<Product> findProductByName(String name) {
        return productFacade.findProductByName(name);
    }
}