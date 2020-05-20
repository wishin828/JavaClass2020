package demos.web.rs;

import demos.db.Product;
import demos.model.ProductManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("discount")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DiscountService {
    
    @Inject
    private ProductManager pm;
    
    @GET
    @Path("{id}")
    public Discount getDiscounted(@PathParam("id") Integer id) {
        Discount discount = new Discount();
        Product product = pm.findProduct(id);
        if (product == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        BigDecimal price = product.getPrice();
        LocalDate date = product.getBestBefore();
        BigDecimal value = price.multiply(BigDecimal.valueOf(0.1));  
        if (price.subtract(value).compareTo(BigDecimal.ONE) > 0 && (date != null && date.compareTo(LocalDate.now()) > 0)) {
            discount.setValue(value);
            discount.setDate(date.minusDays(1));
        }else{
            discount.setValue(BigDecimal.ZERO);
        }
        return discount;
    }   
}
