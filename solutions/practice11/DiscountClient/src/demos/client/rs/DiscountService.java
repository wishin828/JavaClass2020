package demos.client.rs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DiscountService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:7001/pm/rs";

    public DiscountService() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("discount");
    }

    public Discount getDiscount(Integer id) {
        webTarget = webTarget.path(id.toString());
        Response response = webTarget.register(Discount.class).request(MediaType.APPLICATION_JSON).buildGet().invoke();
        Discount discount = response.readEntity(Discount.class);
        return discount;
    }

    public void close() {
        client.close();
    }
}