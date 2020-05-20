package demos.model;

import demos.db.Product;
import java.time.LocalDate;
import java.util.List;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

@Singleton
@LocalBean
public class ExpiringProduct {
    
    private static final Logger logger = Logger.getLogger(ExpiringProduct.class.getName());
    @EJB
    private ProductFacade productFacade;
    @Inject
    private JMSContext context;
    @Resource(lookup="jms/productQueue")
    private Queue productQueue;
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "22", dayOfMonth = "*", year = "*", minute = "52", second = "0")
    public void handleExpiringProducts() {
        JMSProducer producer = context.createProducer();
        List<Product> products = productFacade.findProductByDate(LocalDate.now().plusDays(1));
        products.stream().forEach(p -> producer.send(productQueue, context.createObjectMessage(p)));
    }

}
