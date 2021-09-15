package user.utility;

import org.apache.commons.lang3.StringUtils;

public class PasswordUtility {

    /**
     *
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        if(StringUtils.isBlank(password)){
            return "";
        }
        //You will need to write a better algorithm to generate your password harsh, Don't ever use this approach
        return String.valueOf(password.hashCode());
    }

}
