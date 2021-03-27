package mappers;

import dtos.RequestDto;
import entity.Request;
import utils.AddressUtils;

public class RequestMapper {

    public static RequestDto entityToDto(Request request){
        RequestDto requestDto=new RequestDto();
        requestDto.setRequestTime(request.getRequestTime());
        requestDto.setAddress(AddressMapper.entityToDto(request.getAddress()));
        requestDto.setId(request.getId());
        requestDto.setDocument(DocumentMapper.entityToDto(request.getDocument()));
        requestDto.setRemainingRequests(request.getRemainingRequests());
        requestDto.setApproved(request.getApproved());
        requestDto.setAddressToString(AddressUtils.convertToString(requestDto.getAddress()));
        requestDto.setDocumentType(request.getDocument().getTip());
        requestDto.setRequestTimeString(request.getRequestTime().toString());
        requestDto.setUserName(request.getAddress().getUser().getFirstName()+" "+request.getAddress().getUser().getLastName());
        return requestDto;
    }

    public static Request dtoToEntity(RequestDto requestDto){
        Request request=new Request();
        request.setRequestTime(requestDto.getRequestTime());
        request.setAddress(AddressMapper.dtoToEntity(requestDto.getAddress()));
        request.setId(requestDto.getId());
        request.setDocument(DocumentMapper.dtoToEntity(requestDto.getDocument()));
        request.setRemainingRequests(requestDto.getRemainingRequests());
        request.setApproved(requestDto.getApproved());
        return request;
    }



}
