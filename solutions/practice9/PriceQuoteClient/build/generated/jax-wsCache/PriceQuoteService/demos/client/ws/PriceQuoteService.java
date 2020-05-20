
package demos.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PriceQuoteService", targetNamespace = "http://demos/PriceQuote/Service", wsdlLocation = "http://localhost:7001/demos/PriceQuoteService/PriceQuoteService?WSDL")
public class PriceQuoteService
    extends Service
{

    private final static URL PRICEQUOTESERVICE_WSDL_LOCATION;
    private final static WebServiceException PRICEQUOTESERVICE_EXCEPTION;
    private final static QName PRICEQUOTESERVICE_QNAME = new QName("http://demos/PriceQuote/Service", "PriceQuoteService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:7001/demos/PriceQuoteService/PriceQuoteService?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PRICEQUOTESERVICE_WSDL_LOCATION = url;
        PRICEQUOTESERVICE_EXCEPTION = e;
    }

    public PriceQuoteService() {
        super(__getWsdlLocation(), PRICEQUOTESERVICE_QNAME);
    }

    public PriceQuoteService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PRICEQUOTESERVICE_QNAME, features);
    }

    public PriceQuoteService(URL wsdlLocation) {
        super(wsdlLocation, PRICEQUOTESERVICE_QNAME);
    }

    public PriceQuoteService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PRICEQUOTESERVICE_QNAME, features);
    }

    public PriceQuoteService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PriceQuoteService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PriceQuote
     */
    @WebEndpoint(name = "PriceQuote")
    public PriceQuote getPriceQuote() {
        return super.getPort(new QName("http://demos/PriceQuote/Service", "PriceQuote"), PriceQuote.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PriceQuote
     */
    @WebEndpoint(name = "PriceQuote")
    public PriceQuote getPriceQuote(WebServiceFeature... features) {
        return super.getPort(new QName("http://demos/PriceQuote/Service", "PriceQuote"), PriceQuote.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PRICEQUOTESERVICE_EXCEPTION!= null) {
            throw PRICEQUOTESERVICE_EXCEPTION;
        }
        return PRICEQUOTESERVICE_WSDL_LOCATION;
    }

}
