package demos.client;

import demos.db.Product;
import demos.model.ProductManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

public class ProductClient {

    private static final Logger logger = Logger.getLogger(ProductClient.class.getName());

    public static void main(String[] args) {
        try {
            // Add code that will invoke ProductManager to test remote EJB 
            ProductManager pm = new ProductManager();

//            Product p = pm.findProduct(1);
//            System.out.println(p);

//            List<Product> products = pm.findProductByName("Co%");
//            products.stream().forEach(p -> System.out.println(p));

//            Product p = pm.findProduct(1);
//            p.setPrice(BigDecimal.valueOf(2.5));
//            p.setBestBefore(LocalDate.now().plusDays(1));
//            pm.update(p);

//            Product p = pm.findProduct(1);
//            p.setPrice(BigDecimal.valueOf(0.1));
//            p.setName("x");
//            p.setBestBefore(LocalDate.now().plusDays(1));
//            pm.update(p);

//            Product p = pm.findProduct(1);
//            p.setPrice(BigDecimal.valueOf(5));
//            Scanner s = new Scanner(System.in);
//            System.out.println("Click here and  then press enter to continue");
//            s.nextLine();
//            pm.update(p);

//            Product p = new Product();
//            p.setName("Milk");
//            p.setPrice(BigDecimal.valueOf(2));
//            pm.create(p);

//            Product p = pm.findProduct(5);
//            pm.delete(p);
        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException e = (ConstraintViolationException) cause;
                e.getConstraintViolations().stream().forEach(v -> logger.log(Level.INFO, v.getMessage()));
            } else if (cause instanceof OptimisticLockException) {
                OptimisticLockException e = (OptimisticLockException) cause;
                logger.log(Level.INFO, e.getMessage());
            } else {
                logger.log(Level.SEVERE, "Product Manager Error", ex);
            }
        }
    }
}
