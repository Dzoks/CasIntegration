package rs.dzoks.admin_application.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DocumentTypeHasDrivingCategoryPK implements Serializable {
    private Integer drivingCategoryId;
    private Integer documentId;

    @Column(name = "driving_category_id", nullable = false)
    @Id
    public Integer getDrivingCategoryId() {
        return drivingCategoryId;
    }

    public void setDrivingCategoryId(Integer drivingCategoryId) {
        this.drivingCategoryId = drivingCategoryId;
    }

    @Column(name = "document_id", nullable = false)
    @Id
    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentTypeHasDrivingCategoryPK that = (DocumentTypeHasDrivingCategoryPK) o;
        return Objects.equals(drivingCategoryId, that.drivingCategoryId) &&
                Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drivingCategoryId, documentId);
    }
}
