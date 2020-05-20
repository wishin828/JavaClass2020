package demos.client;

import demos.client.ws.PriceQuote;
import demos.client.ws.PriceQuoteService;
import demos.client.ws.Products;
import demos.client.ws.Quote;
import demos.client.ws.QuoteFault;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PriceQuoteClient {

    private static final Logger logger = Logger.getLogger(PriceQuoteClient.class.getName());
    
    public static void main(String[] args) {
        try{
            PriceQuoteService service = new PriceQuoteService();
            PriceQuote port = service.getPriceQuote();
            Products request = new Products();
            List<Integer> ids = request.getProductId();
            ids.add(1);
            ids.add(3);
            Quote quote = port.getQuote(request);
            System.out.println(quote.getQuotedPrice());
        } catch (QuoteFault ex) {
           logger.log(Level.INFO, ex.getFaultInfo().getMessage());
        }catch (Exception ex) {
           logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
}
