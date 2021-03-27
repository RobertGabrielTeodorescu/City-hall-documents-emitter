package mappers;

import dtos.AddressDto;
import dtos.LoggedInUserDto;
import entity.Address;
import entity.User;

import java.util.HashSet;
import java.util.Set;

public class LoggedInUserMapper {

    public static LoggedInUserDto entityToDto(User user){
        LoggedInUserDto loggedInUserDto=new LoggedInUserDto();
        Set<AddressDto> addressDtoSet=new HashSet<>();
        for(Address address:user.getAddresses()){
            addressDtoSet.add(AddressMapper.entityToDto(address));
        }
        loggedInUserDto.setAddressSet(addressDtoSet);
        loggedInUserDto.setId(user.getId());
        return loggedInUserDto;
    }

    public static void dtoToEntity(LoggedInUserDto loggedInUserDto,User user){
        Set<Address> addresses=new HashSet<>();
        for(AddressDto addressDto: loggedInUserDto.getAddressSet()){
            addresses.add(AddressMapper.dtoToEntity(addressDto));
        }
        user.setAddresses(addresses);
        user.setId(loggedInUserDto.getId());
    }

}
