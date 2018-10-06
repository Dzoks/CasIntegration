package rs.dzoks.service.soap;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "documents",
})
@XmlRootElement(name = "GetDocumentsResponse")
public class GetDocumentResponse {
    @XmlElement(required = true)
    protected String documents;
    public void setDocuments(String value) {
        this.documents = value;
    }

    public String getDocuments() {
        return documents;
    }
}
