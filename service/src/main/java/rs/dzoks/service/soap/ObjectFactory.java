//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.10.05 at 12:33:48 PM CEST 
//


package rs.dzoks.service.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.dzoks.service.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.dzoks.service.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDocumentsRequest }
     * 
     */
    public GetDocumentsRequest createGetDocumentsRequest() {
        return new GetDocumentsRequest();
    }

    /**
     * Create an instance of {@link GetDocumentsResponse }
     * 
     */
    public GetDocumentsResponse createGetDocumentsResponse() {
        return new GetDocumentsResponse();
    }

    /**
     * Create an instance of {@link DocumentsListSOAP }
     * 
     */
    public DocumentsListSOAP createDocumentsListSOAP() {
        return new DocumentsListSOAP();
    }

    /**
     * Create an instance of {@link DocumentSOAP }
     * 
     */
    public DocumentSOAP createDocumentSOAP() {
        return new DocumentSOAP();
    }

    /**
     * Create an instance of {@link DrivingCategorySOAP }
     * 
     */
    public DrivingCategorySOAP createDrivingCategorySOAP() {
        return new DrivingCategorySOAP();
    }

}
