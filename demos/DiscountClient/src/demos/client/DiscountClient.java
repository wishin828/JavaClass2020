package demos.client;

import demos.client.rs.Discount;
import demos.client.rs.DiscountService;
import java.math.BigDecimal;

public class DiscountClient {

    public static void main(String[] args) {
        DiscountService ds = new DiscountService();
        Discount discount = ds.getDiscount(new Integer(42));
        if (discount.getValue().compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("Possible discount of " + discount.getValue() +", becomes available on "+discount.getDate());
        }else{
            System.out.println("This product is not eligible for the discount");
        }
        ds.close();
    }
}
