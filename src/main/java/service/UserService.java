package service;

import dtos.AddressDto;
import dtos.LoggedInUserDto;
import dtos.UserDto;
import entity.Address;
import entity.User;
import exceptions.CustomUserExceptions;
import mappers.AddressMapper;
import mappers.LoggedInUserMapper;
import mappers.UserMapper;
import repository.UserRepo;
import validators.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    private UserRepo userRepo;
    private UserValidator userValidator;

    public UserService(){
        userRepo=new UserRepo();
        userValidator=new UserValidator();
    }

    public void addUser(UserDto userDto){
        userDto.setId(UUID.randomUUID().toString());
        User user= UserMapper.dtoToEntity(userDto);
        userValidator.validateAddNewUserFlow(user);
        if(userRepo.findByEmail(user.getEmail())!=null){
            throw new IllegalArgumentException(CustomUserExceptions.EMAIL_ALREADY_EXISTS);
        }
        userRepo.insertNewUser(user);
    }

    public LoggedInUserDto getLoggingUserByEmailAndPassword(String email,String password){
        User user=userRepo.findByEmailAndPassword(email,password);
        if(user==null){
            throw new IllegalArgumentException(CustomUserExceptions.INVALID_LOGIN);
        }
        return LoggedInUserMapper.entityToDto(user);
    }

    public LoggedInUserDto updateLoggedUser(String id){
        User user=userRepo.findById(id);
        return LoggedInUserMapper.entityToDto(user);
    }

    public void addAddressToUser(String id, AddressDto addressDto){
        User user=userRepo.findById(id);
        Address address= AddressMapper.dtoToEntity(addressDto);
        user.addAddress(address);
        userRepo.updateUser(user);
    }

    public void removeAddressFromUser(String id, AddressDto addressDto){
        User user=userRepo.findById(id);
        Address address=AddressMapper.dtoToEntity(addressDto);
        user.removeAddress(address);
        userRepo.updateUser(user);
    }

    public List<UserDto> getAllUsers(){
        List<User> users=userRepo.findAllUsers();
        List<UserDto> userDtos=new ArrayList<>();
        for(User user:users){
            userDtos.add(UserMapper.entityToDto(user));
        }
        return userDtos;
    }




}
