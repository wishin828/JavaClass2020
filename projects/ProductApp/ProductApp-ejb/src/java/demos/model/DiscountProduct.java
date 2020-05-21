/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.model;

import demos.db.Product;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Admin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/productQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName="messageSelector",propertyValue="JMSXDeliveryCount < 3")
})
public class DiscountProduct implements MessageListener {
    private static final Logger logger =Logger.getLogger(DiscountProduct.class.getName());
    
    @EJB
    private ProductFacade productFacade;
    public DiscountProduct() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            logger.log(Level.INFO,"message run");
            Product product = message.getBody(Product.class);
            BigDecimal discount =product.getPrice().multiply(BigDecimal.valueOf(0.1));
            product.setPrice(product.getPrice().subtract(discount));
            productFacade.validateProduct(product);
            productFacade.update(product);
            logger.log(Level.INFO, "Product "+product+" discounted by "+discount);
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, "Error Acquiring Message", ex);
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
    
}
