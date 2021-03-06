package demos.model;

import demos.db.Product;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("pm")
public class ProductManager {

    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
    @EJB
    private ProductFacade productFacade;
    private List<Product> products;
    private Product product;
    private String status;
    private boolean errors;
    
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }
    
}