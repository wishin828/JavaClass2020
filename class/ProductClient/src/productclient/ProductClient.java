/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productclient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Admin
 */
public class ProductClient {

    @Inject
    private EJBContext context;
    private static final Logger logger=Logger.getLogger(ProductClient.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ProductManager pm=new ProductManager("ProductClientPU");
            Products p=pm.findProduct(1);
            
            p.setName("x");
            p.setPrice(BigDecimal.valueOf(0.1));
            p.setBestBefore(LocalDate.now().plusDays(1));
            pm.update(p);
            
            System.out.println(p.toString()+" loading ok!!!!!!!!!");
            pm.closeEntityManager();
        }
        catch(Exception ex){
            Throwable cause=ex.getCause();
            if(cause instanceof ConstraintViolationException){
                ConstraintViolationException e=(ConstraintViolationException)cause;
            }
            else if(cause instanceof OptimisticLockException){
                OptimisticLockException e=(OptimisticLockException)cause;
                logger.log(Level.INFO,e.getMessage());
            }else{
                logger.log(Level.SEVERE,"Product Manager Error",ex);
            }
        }
    }
    
}
