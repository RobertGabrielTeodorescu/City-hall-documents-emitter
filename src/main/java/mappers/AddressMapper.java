package mappers;

import dtos.AddressDto;
import entity.Address;

public class AddressMapper {

    public static AddressDto entityToDto(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setApartment(address.getApartment());
        addressDto.setBlock(address.getBlock());
        addressDto.setUser(address.getUser());
        addressDto.setCity(address.getCity());
        addressDto.setCounty(address.getCounty());
        addressDto.setId(address.getId());
        addressDto.setFloor(address.getFloor());
        addressDto.setStreet(address.getStreet());
        addressDto.setNr(address.getNr());
        addressDto.setRequests(address.getRequests());
        return addressDto;
    }

    public static Address dtoToEntity(AddressDto addressDto){
        Address address=new Address();
        address.setId(addressDto.getId());
        address.setApartment(addressDto.getApartment());
        address.setBlock(addressDto.getBlock());
        address.setUser(addressDto.getUser());
        address.setCity(addressDto.getCity());
        address.setCounty(addressDto.getCounty());
        address.setId(addressDto.getId());
        address.setFloor(addressDto.getFloor());
        address.setStreet(addressDto.getStreet());
        address.setNr(addressDto.getNr());
        address.setRequests(addressDto.getRequests());
        return address;
    }


}
