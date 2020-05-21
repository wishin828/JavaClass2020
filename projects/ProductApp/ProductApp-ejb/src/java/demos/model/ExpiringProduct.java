/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.model;

import demos.db.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

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
    @Inject
    private JMSContext context;
    @Resource(lookup="jms/productQueue")
    private Queue productQueue;
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "15", dayOfMonth = "*",year = "*", minute = "19", second = "0")
    public void handleExpiringProducts() {
        try {
            List<Product> products =productFacade.findProductByDate(LocalDate.now().plusDays(1));
            products.stream().forEach(p -> logger.log(Level.INFO,p.toString()));
        
            JMSProducer producer = context.createProducer();
            products.stream().forEach(p -> 
                {
                    logger.log(Level.INFO,p.toString());
                    producer.send(productQueue,context.createObjectMessage(p));
                }
            );

        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException)cause;
            e.getConstraintViolations().stream()
            .forEach(v -> logger.log(Level.INFO,v.getMessage()));
            } else if (cause instanceof OptimisticLockException) {
            OptimisticLockException e =(OptimisticLockException)cause;
            logger.log(Level.INFO, e.getMessage());
            } else {
            logger.log(Level.SEVERE, "Product Manager Error",ex);
            }
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
