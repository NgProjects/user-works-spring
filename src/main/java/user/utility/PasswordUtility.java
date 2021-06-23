package user.utility;

public class PasswordUtility {

    /**
     *
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        if(password == null || password.isEmpty()){
            return "";
        }
        //You will need to write a better algorithm to generate your password harsh, Don't ever use this approach
        return String.valueOf(password.hashCode());
    }

}
