package mappers;

import dtos.UserDto;
import entity.User;

public class UserMapper {

    public static UserDto entityToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddressSet(user.getAddresses());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user=new User();
        user.setAddresses(userDto.getAddressSet());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
        user.setStatus(userDto.getStatus());
        return user;
    }

}
