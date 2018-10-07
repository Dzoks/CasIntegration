package rs.dzoks.admin_application.model.modelCustom;

import rs.dzoks.admin_application.model.Document;
import rs.dzoks.admin_application.model.DocumentHasDrivingCategory;
import java.util.List;


public class DocumentExtended {
    private Document document;
    private List<DocumentHasDrivingCategory> categories;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<DocumentHasDrivingCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<DocumentHasDrivingCategory> categories) {
        this.categories = categories;
    }
}
