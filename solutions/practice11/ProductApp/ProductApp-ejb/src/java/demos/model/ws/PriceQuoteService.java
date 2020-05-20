package demos.model.ws;

import demos.model.ProductFacade;
import demos.pricequote.Products;
import demos.pricequote.Quote;
import demos.pricequote.QuoteError;
import demos.pricequote.service.QuoteFault;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService(serviceName = "PriceQuoteService", portName = "PriceQuote", endpointInterface = "demos.pricequote.service.PriceQuote", targetNamespace = "http://demos/PriceQuote/Service", wsdlLocation = "META-INF/wsdl/PriceQuoteService/PriceQuoteService.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@Stateless
public class PriceQuoteService {

    private static final Logger logger = Logger.getLogger(PriceQuoteService.class.getName());
    @EJB
    private ProductFacade productFacade;
    
    public Quote getQuote(Products request) throws QuoteFault {
            try {
                List<Integer> productIds = request.getProductId();
                if (productIds.isEmpty()) {
                    QuoteError error = new QuoteError();
                    error.setMessage("Provide at least one product id to get a quote");
                    throw new QuoteFault("Error producing price quote",error);
                }
                Object[] values = productFacade.findTotal(productIds);
                Long count = (Long)values[0];
                if (count < productIds.size()) {
                    QuoteError error = new QuoteError();
                    error.setMessage("Unable to locate some of these products: "+productIds);
                    throw new QuoteFault("Error producing price quote",error);
                }
                BigDecimal quotedPrice = (BigDecimal)values[1];
                Quote quote = new Quote();
                quote.setQuotedPrice(quotedPrice);
                return quote;
            } catch (QuoteFault ex) {
                logger.log(Level.INFO,"Error producing price quote",ex);
                throw ex;
            } catch (Exception ex) {
                logger.log(Level.INFO,"Error producing price quote",ex);
                QuoteError error = new QuoteError();
                error.setMessage(ex.getMessage());
                throw new QuoteFault("Error producing price quote",error);
            }
    }
    
}
