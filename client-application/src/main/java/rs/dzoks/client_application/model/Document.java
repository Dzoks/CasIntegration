package rs.dzoks.client_application.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Document {
    private Integer id;
    private Integer documentTypeId;
    private Integer userId;
    private String serialNumber;
    private String residence;
    private String issuingAuthority;
    private Timestamp dateOfIssue;
    private Timestamp validUntil;
    private String citizenship;
    private String entityCitizenship;
    private String countryCode;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "document_type_id", nullable = false)
    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "serial_number", nullable = false, length = 10)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "residence", nullable = false, length = 45)
    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    @Basic
    @Column(name = "issuing_authority", nullable = false, length = 45)
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    @Basic
    @Column(name = "date_of_issue", nullable = false)
    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Basic
    @Column(name = "valid_until", nullable = false)
    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }

    @Basic
    @Column(name = "citizenship", nullable = true, length = 45)
    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Basic
    @Column(name = "entity_citizenship", nullable = true, length = 45)
    public String getEntityCitizenship() {
        return entityCitizenship;
    }

    public void setEntityCitizenship(String entityCitizenship) {
        this.entityCitizenship = entityCitizenship;
    }

    @Basic
    @Column(name = "country_code", nullable = true, length = 3)
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id) &&
                Objects.equals(documentTypeId, document.documentTypeId) &&
                Objects.equals(userId, document.userId) &&
                Objects.equals(serialNumber, document.serialNumber) &&
                Objects.equals(residence, document.residence) &&
                Objects.equals(issuingAuthority, document.issuingAuthority) &&
                Objects.equals(dateOfIssue, document.dateOfIssue) &&
                Objects.equals(validUntil, document.validUntil) &&
                Objects.equals(citizenship, document.citizenship) &&
                Objects.equals(entityCitizenship, document.entityCitizenship) &&
                Objects.equals(countryCode, document.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentTypeId, userId, serialNumber, residence, issuingAuthority, dateOfIssue, validUntil, citizenship, entityCitizenship, countryCode);
    }
}
