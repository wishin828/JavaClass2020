/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.model;

import demos.db.Product;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author Admin
 */
@Singleton
@LocalBean

public class ExpiringProduct {
    private static final Logger logger =Logger.getLogger(ExpiringProduct.class.getName());
    @EJB
    private ProductFacade productFacade;
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "16", dayOfMonth = "*",year = "*", minute = "5", second = "0")
    public void handleExpiringProducts() {
        List<Product> products =productFacade.findProductByDate(LocalDate.now().plusDays(1));
        products.stream().forEach(p -> logger.log(Level.INFO,p.toString()));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
