package demos.model;

import demos.db.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProductManager {

    @EJB
    private ProductFacade productFacade;
    
    public ProductManager() {
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
        return productFacade.findProduct(id);
    }
    public List<Product> findProductByName(String name) {
        return productFacade.findProductByName(name);
    }
}