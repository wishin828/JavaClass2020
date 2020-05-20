
package demos.pricequote.service;

import javax.xml.ws.WebFault;
import demos.pricequote.QuoteError;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "QuoteError", targetNamespace = "http://demos/PriceQuote")
public class QuoteFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private QuoteError faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public QuoteFault(String message, QuoteError faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public QuoteFault(String message, QuoteError faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: demos.pricequote.QuoteError
     */
    public QuoteError getFaultInfo() {
        return faultInfo;
    }

}