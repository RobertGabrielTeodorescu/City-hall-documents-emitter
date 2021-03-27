package validators;

import entity.User;
import exceptions.CustomUserExceptions;

public class UserValidator {

    public void validateAddNewUserFlow(User user){
        if(user==null){
            throw new IllegalArgumentException(CustomUserExceptions.USER_NULL);
        }
        if(user.getFirstName().equals("") ||user.getLastName().equals("")){
            throw new IllegalArgumentException(CustomUserExceptions.NAME_INVALID);
        }
        if(user.getEmail().equals("")){
            throw new IllegalArgumentException(CustomUserExceptions.EMAIL_NULL);
        }
        if(!user.getEmail().contains("@")){
            throw new IllegalArgumentException(CustomUserExceptions.EMAIL_INVALID_FORMAT);
        }
        if(user.getPassword().equals("")){
            throw new IllegalArgumentException(CustomUserExceptions.PASSWORD_NULL);
        }
        if(user.getPassword().length()<6){
            throw new IllegalArgumentException(CustomUserExceptions.PASSWORD_INVALID_FORMAT);
        }
    }



}
