/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Admin
 */
public class ProductClient {

    private static final Logger logger=Logger.getLogger(ProductClient.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ProductManager pm=new ProductManager("ProductClientPU");
            Products p=pm.findProduct(1);
            System.out.println(p.getName()+" loading ok!!!!!!!!!");
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
