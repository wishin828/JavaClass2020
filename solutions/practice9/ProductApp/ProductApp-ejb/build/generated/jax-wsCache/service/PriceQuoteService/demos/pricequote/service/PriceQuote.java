
package demos.pricequote.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import demos.pricequote.ObjectFactory;
import demos.pricequote.Products;
import demos.pricequote.Quote;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PriceQuote", targetNamespace = "http://demos/PriceQuote/Service")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PriceQuote {


    /**
     * 
     * @param request
     * @return
     *     returns demos.pricequote.Quote
     * @throws QuoteFault
     */
    @WebMethod
    @WebResult(name = "Quote", targetNamespace = "http://demos/PriceQuote", partName = "response")
    public Quote getQuote(
        @WebParam(name = "Products", targetNamespace = "http://demos/PriceQuote", partName = "request")
        Products request)
        throws QuoteFault
    ;

}
