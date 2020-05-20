package demos.model;

import demos.db.Product;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;

@SessionScoped
@Named("pm")
public class ProductManager implements Serializable {

    @EJB
    private ProductFacade productFacade;
    private List<Product> products;
    private Product product;
    private String status;
    private boolean errors;
    private String name;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String showList() {
        products = productFacade.findProductByName(name);
        if (products.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No products matching name '"+name+"' found",null);
            context.addMessage(null, message);
            return null;
        }
        return "list";
    }
    public String showEdit() {
        Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("p_id"));
        product = products.stream().filter(p->p.getId().equals(id)).findFirst().get();
        return "edit";
    }
    public void handleUpdate(ActionEvent event) {
        FacesMessage message;
        try {
            productFacade.update(product);
            product = productFacade.findProduct(product.getId());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null);
        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof OptimisticLockException) {
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product has been changed by another user.", null);
            } else {
                throw ex;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}