package mappers;

import dtos.DocumentDto;
import entity.Document;

public class DocumentMapper {

    public static DocumentDto entityToDto(Document document){
        DocumentDto documentDto=new DocumentDto();
        documentDto.setId(document.getId());
        documentDto.setTip(document.getTip());
        documentDto.setRequests(document.getRequests());
        return documentDto;
    }

    public static Document dtoToEntity(DocumentDto documentDto){
        Document document=new Document();
        document.setId(documentDto.getId());
        document.setTip(documentDto.getTip());
        document.setRequests(documentDto.getRequests());
        return document;
    }


}
