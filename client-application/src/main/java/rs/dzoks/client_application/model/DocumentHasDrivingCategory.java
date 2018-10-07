package rs.dzoks.client_application.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "document_has_driving_category", schema = "documents_db", catalog = "")
@IdClass(DocumentHasDrivingCategoryPK.class)
public class DocumentHasDrivingCategory {
    private Integer drivingCategoryId;
    private Integer documentId;
    private Timestamp examDate;

    @Id
    @Column(name = "driving_category_id", nullable = false)
    public Integer getDrivingCategoryId() {
        return drivingCategoryId;
    }

    public void setDrivingCategoryId(Integer drivingCategoryId) {
        this.drivingCategoryId = drivingCategoryId;
    }

    @Id
    @Column(name = "document_id", nullable = false)
    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "exam_date", nullable = false)
    public Timestamp getExamDate() {
        return examDate;
    }

    public void setExamDate(Timestamp examDate) {
        this.examDate = examDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentHasDrivingCategory that = (DocumentHasDrivingCategory) o;
        return Objects.equals(drivingCategoryId, that.drivingCategoryId) &&
                Objects.equals(documentId, that.documentId) &&
                Objects.equals(examDate, that.examDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drivingCategoryId, documentId, examDate);
    }
}
