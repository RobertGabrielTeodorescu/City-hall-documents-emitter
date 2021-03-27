package service;

import dtos.AddressDto;
import dtos.DocumentDto;
import dtos.LoggedInUserDto;
import dtos.RequestDto;
import entity.Address;
import entity.Document;
import entity.Request;
import exceptions.CustomRequestExceptions;
import mappers.AddressMapper;
import mappers.DocumentMapper;
import mappers.RequestMapper;
import repository.RequestRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestService {

    private RequestRepo requestRepo=new RequestRepo();

    public void updateRequest(RequestDto requestDto){
        if(requestDto==null){
            throw new IllegalArgumentException(CustomRequestExceptions.NO_REQUEST_SELECTED);
        }
        requestRepo.updateRequest(RequestMapper.dtoToEntity(requestDto));
    }

    public void addRequest(RequestDto requestDto){
        requestRepo.insertNewRequest(RequestMapper.dtoToEntity(requestDto));
    }


    public void deleteRequest(RequestDto requestDto){
        if(requestDto==null){
            throw new IllegalArgumentException(CustomRequestExceptions.NO_REQUEST_SELECTED);
        }
        requestRepo.deleteRequest(RequestMapper.dtoToEntity(requestDto));
    }

    public List<RequestDto> getRequestsByLoggedUser(LoggedInUserDto loggedInUserDto){
        List<RequestDto> requestDtos=new ArrayList<>();
        Set<AddressDto> addresses=loggedInUserDto.getAddressSet();
        for(AddressDto addressDto:addresses){
            Set<Request> requests=addressDto.getRequests();
            for(Request request:requests){
                requestDtos.add(RequestMapper.entityToDto(request));
            }
        }
        return requestDtos;
    }

    public List<RequestDto> getAllRequests(){
        List<Request> requests= requestRepo.findAllRequests();
        List<RequestDto> requestDtos=new ArrayList<>();
        for(Request request:requests){
            requestDtos.add(RequestMapper.entityToDto(request));
        }
        return requestDtos;
    }

    public int getRequestsByAddressDocumentYear(AddressDto addressDto, DocumentDto documentDto, LocalDateTime localDateTime){
        Address address=AddressMapper.dtoToEntity(addressDto);
        Document document=DocumentMapper.dtoToEntity(documentDto);
        int nr=requestRepo.findRequestsByAddressDocumentYear(address,document,localDateTime).size();
        if(nr==3){
            throw new IllegalArgumentException(CustomRequestExceptions.NO_MORE_REQUESTS_ALLOWED);
        }
        return nr;
    }

}
