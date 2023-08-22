import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //takes in user input
        try {
        System.out.print("Enter your username: ");

            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.next();

            // creates a login object, so I can run "isCorrect"
            Login login = new Login();
            boolean isCorrect = login.isCorrect(username, password);

            if (isCorrect){
                System.out.println("Welcome, Boss!");
            } else
            {
                System.out.println("Access  Denied");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
