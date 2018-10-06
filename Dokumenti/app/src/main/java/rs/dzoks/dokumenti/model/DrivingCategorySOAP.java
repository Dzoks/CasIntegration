package rs.dzoks.dokumenti.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class DrivingCategorySOAP implements Serializable {

    @Expose
    protected String category;
    @Expose
    protected long examDate;

    /**
     * Gets the value of the category property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the examDate property.
     */
    public long getExamDate() {
        return examDate;
    }

    /**
     * Sets the value of the examDate property.
     */
    public void setExamDate(long value) {
        this.examDate = value;
    }

}
