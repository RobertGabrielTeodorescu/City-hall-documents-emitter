package service;

import dtos.DocumentDto;
import dtos.RequestDto;
import entity.Document;
import entity.Request;
import exceptions.CustomDocumentExceptions;
import exceptions.CustomRequestExceptions;
import mappers.DocumentMapper;
import mappers.RequestMapper;
import repository.DocumentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentService {

    private DocumentRepo documentRepo=new DocumentRepo();

    public void addDocument(DocumentDto documentDto){
        if(documentDto.getTip().equals("")){
            throw new IllegalArgumentException(CustomDocumentExceptions.NO_TYPE_INSERTED);
        }
        if(documentRepo.findByName(documentDto.getTip())==null){
            Document document=DocumentMapper.dtoToEntity(documentDto);
            document.setId(UUID.randomUUID().toString());
            documentRepo.insertNewDocument(document);
        }
        else{
            throw new IllegalArgumentException(CustomDocumentExceptions.DOCUMENT_ALREADY_EXISTS);
        }
    }

    public void removeDocument(DocumentDto documentDto){
        if(documentDto==null){
            throw new IllegalArgumentException(CustomDocumentExceptions.NO_DOCUMENT_SELECTED);
        }
        documentRepo.deleteDocument(DocumentMapper.dtoToEntity(documentDto));
    }

    public List<DocumentDto> getAllDocuments(){
        List<DocumentDto> documentDtos=new ArrayList<>();
        List<Document> documents=documentRepo.findAllDocuments();
        for(Document document:documents){
            documentDtos.add(DocumentMapper.entityToDto(document));
        }
        return documentDtos;
    }

    public void updateDocument(DocumentDto documentDto){
        documentRepo.updateDocument(DocumentMapper.dtoToEntity(documentDto));
    }



    public DocumentDto addRequestToDocument(DocumentDto documentDto, RequestDto requestDto){
        if(documentDto==null){
            throw new IllegalArgumentException(CustomRequestExceptions.NO_DOCUMENT);
        }
        Document document=DocumentMapper.dtoToEntity(documentDto);
        Request request= RequestMapper.dtoToEntity(requestDto);
        document.addRequest(request);
        requestDto.setDocument(DocumentMapper.entityToDto(document));
        return DocumentMapper.entityToDto(document);
    }

    public DocumentDto removeRequestFromDocument(DocumentDto documentDto, RequestDto requestDto){
        Document document=DocumentMapper.dtoToEntity(documentDto);
        Request request=RequestMapper.dtoToEntity(requestDto);
        document.removeRequest(request);
        return DocumentMapper.entityToDto(document);
    }


}
