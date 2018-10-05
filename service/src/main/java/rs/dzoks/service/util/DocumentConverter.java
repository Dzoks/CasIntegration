package rs.dzoks.service.util;


import rs.dzoks.service.model.*;
import rs.dzoks.service.soap.DocumentSOAP;
import rs.dzoks.service.soap.DrivingCategorySOAP;


import java.util.List;

public class DocumentConverter {

    public static DocumentSOAP convertDocument(Document document, User user, List<DocumentHasDrivingCategory> categories, List<DrivingCategory> globalCategories, List<DocumentType> globalDocumentTypes){
        DocumentSOAP documentToReturn=new DocumentSOAP();
        String documentType=null;
        for (DocumentType id:globalDocumentTypes){
            if (document.getDocumentTypeId().equals(id.getId())){
                documentType=id.getValue();
                break;
            }
        }
        documentToReturn.setFname(user.getFname());
        documentToReturn.setId(document.getId());
        documentToReturn.setCitizenship(document.getCitizenship());
        documentToReturn.setBirthDate(user.getBirthDate().getTime());
        documentToReturn.setCountryCode(document.getCountryCode());
        documentToReturn.setDateOfIssue(document.getDateOfIssue().getTime());
        documentToReturn.setDocumentType(documentType);
        documentToReturn.setEntityCitizenship(document.getEntityCitizenship());
        documentToReturn.setIssuingAuthority(document.getIssuingAuthority());
        documentToReturn.setJmbg(user.getJmbg());
        documentToReturn.setLname(user.getLname());
        documentToReturn.setMale(user.getMale());
        documentToReturn.setPlaceOfBirth(user.getPlaceOfBirth());
        documentToReturn.setResidence(document.getResidence());
        documentToReturn.setSerialNumber(document.getSerialNumber());
        documentToReturn.setValidUntil(document.getValidUntil().getTime());
        if (categories!=null){
            for(DocumentHasDrivingCategory cat:categories){
                DrivingCategorySOAP categorySOAP=new DrivingCategorySOAP();
                categorySOAP.setExamDate(cat.getExamDate().getTime());
                String categoryName=null;
                for(DrivingCategory id:globalCategories){
                    if (id.getId().equals(cat.getDrivingCategoryId())){
                        categoryName=id.getValue();
                        break;
                    }
                }
                categorySOAP.setCategory(categoryName);
                documentToReturn.getCategories().add(categorySOAP);
            }
        }
        return documentToReturn;
    }



}
