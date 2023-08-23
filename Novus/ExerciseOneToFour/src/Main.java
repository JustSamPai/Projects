import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //takes user input
        Scanner reader = new Scanner(System.in);
        System.out.print("What times table would you like? ");
        int tableSize = reader.nextInt();
        //creates a 2d array for times tables
        int[][] timesTable = new int[tableSize][tableSize];

        //nested for loop for the 2d array
        for (int i = 1; i <= tableSize; i++) {
            for (int j = 1; j <= tableSize; j++) {
                //gets the values for i and j (values that will be in a table)
                timesTable[i - 1][j - 1] = i * j;
                //outputs the times table in a grid/table
                System.out.print(timesTable[i - 1][j - 1] + "\t");
            }
            System.out.println();
        }
    }
}
