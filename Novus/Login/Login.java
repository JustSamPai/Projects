import java.util.Objects;

public class Login {
    //this class will have all the components needed for a login system
    //init username and password will be a string
    //maybe some encryption

    //take in user data
    // should be of type string -> try catch

    private String password = "password";
    private String username = "admin";

    //this checks if the perameters match the variables above
    public boolean isCorrect(String username, String password){

        return this.password.equals(password) && this.username.equals(username);
    }


}
