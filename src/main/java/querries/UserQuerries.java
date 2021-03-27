package querries;

public class UserQuerries {

    public static String FIND_USER_BY_EMAIL="FROM User u WHERE u.email= :user_email";
    public static String FIND_USER_BY_EMAIL_AND_PASSWORD="FROM User u WHERE u.email=:user_email and u.password=:user_password";
    public static String FIND_USER_BY_ID="FROM User u WHERE u.id=:user_id";
    public static String FIND_ALL_USERS="FROM User u";

}
