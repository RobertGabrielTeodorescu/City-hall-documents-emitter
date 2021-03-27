package service;

import dtos.AddressDto;
import dtos.RequestDto;
import entity.Address;
import entity.Request;
import exceptions.CustomAddressExceptions;
import exceptions.CustomRequestExceptions;
import mappers.AddressMapper;
import mappers.RequestMapper;
import repository.AddressRepo;

import java.util.UUID;

public class AddressService {

    private AddressRepo addressRepo=new AddressRepo();


    public void addAddress(AddressDto addressDto){
        addressDto.setId(UUID.randomUUID().toString());
        Address address= AddressMapper.dtoToEntity(addressDto);
        addressRepo.insertNewAddress(address);
    }

    public void deleteAddress(AddressDto addressDto){
        if(addressDto==null){
            throw new IllegalArgumentException(CustomAddressExceptions.NO_ADDRESS_SELECTED);
        }
        addressRepo.deleteAddress(AddressMapper.dtoToEntity(addressDto));
    }

    public void updateAddress(AddressDto addressDto){
        addressRepo.updateAddress(AddressMapper.dtoToEntity(addressDto));
    }


    public AddressDto addRequestToAddress(AddressDto addressDto, RequestDto requestDto){
        if(addressDto==null){
            throw new IllegalArgumentException(CustomRequestExceptions.NO_ADDRESS);
        }
        Address address=AddressMapper.dtoToEntity(addressDto);
        Request request= RequestMapper.dtoToEntity(requestDto);
        address.addRequest(request);
        requestDto.setAddress(AddressMapper.entityToDto(address));
        return AddressMapper.entityToDto(address);
    }

    public AddressDto removeRequestFromAddress(AddressDto addressDto,RequestDto requestDto){
        Address address=AddressMapper.dtoToEntity(addressDto);
        Request request=RequestMapper.dtoToEntity(requestDto);
        address.removeRequest(request);
        return AddressMapper.entityToDto(address);
    }


}
