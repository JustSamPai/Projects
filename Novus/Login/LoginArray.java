import java.util.Objects;
import java.util.Scanner;

public class LoginArray {

//must enter a username
//username is not case sensitive
//allows 3 attempts -> access denied
//have 5 usernames and passwords in an array

    public static void main(String[] args) {
        //init username + password
        String username = "admin";
        String password = "password";
        String inputUsername = "";
        boolean firstInput = true;
        // create an array for username and passwords
        String[] usernameArr = {"test1", "test2", "test3"};
        String[] passwordArr = {"1", "2", "3"};
        int index = -1;



        //compare userinput to the array
        //if there is a match get the index
        //check the password array for that index

        //take in user input
        Scanner scanner = new Scanner(System.in);

        //TODO make this not case sensitive
        //TODO must enter username
        while(Objects.equals(inputUsername, "")) {
            if (firstInput){
                System.out.print("Enter username: ");
                firstInput = false;
            }else {
                System.out.print("Please enter a valid username: ");
            }
            //checks user input on next line converts it to lowercase and trims any white spaces
            inputUsername = scanner.nextLine().trim().toLowerCase();
        }



        //TODO give 3 attempts
        //check if user input matches Username+Password
//        if (username.equals(inputUsername) && password.equals(inputPassword)){
//            System.out.println("Welcome, Boss Man!");
//        } else{
//            System.out.println("access denied");
//        }
        for(int i = 0; i < usernameArr.length; i++){
            if(usernameArr[i].equalsIgnoreCase(inputUsername)){
                index = i;
                break;
            }
        }

        try {
            for (int j = 0; j < 3; j++) {
                //ask for password
                System.out.print("Enter password: ");
                String inputPassword = scanner.nextLine();
                if (inputPassword.equals(passwordArr[index])) {
                    System.out.println("welcome boss");
                    break;
                } else {
                    System.out.println("Access Denied");
                }
            }
        } catch (Exception e){
            System.out.println("user does not exist ");
        }

        //
    }
}
