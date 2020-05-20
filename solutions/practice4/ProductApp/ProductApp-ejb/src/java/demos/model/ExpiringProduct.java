package demos.model;

import demos.db.Product;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class ExpiringProduct {
    
    private static final Logger logger = Logger.getLogger(ExpiringProduct.class.getName());
    @EJB
    private ProductFacade productFacade;
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "18", dayOfMonth = "*", year = "*", minute = "30", second = "0")
    public void handleExpiringProducts() {
        List<Product> products = productFacade.findProductByDate(LocalDate.now().plusDays(1));
        products.stream().forEach(p -> logger.log(Level.INFO, p.toString()));
    }

}
