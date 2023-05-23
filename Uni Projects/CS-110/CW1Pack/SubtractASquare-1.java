import java.util.Scanner;
public class SubtractASquare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // initializes all variables for player 1, 2 and the piles
        final int NUM_IN_PILE = 13;
        final int THREE = 3;
        int pile1 = NUM_IN_PILE;
        int pile2 = NUM_IN_PILE;
        int pile3 = NUM_IN_PILE;
        int winner = 0;
        int amount = 0;
        int pileNumber;
        int pile2Number;
        int player2Input = 0;
        String stringPlayer1Input;
        String stringPlayer2Input;
        String stringPileNumber = "0";
        String stringPile2Number = " ";
        System.out.println("Remaining coins: " + pile1 + ", " + pile2 + ", " + pile3);
        // loops the program as long as a pile has more than 0 coins
        while (pile1 > 0 || pile2 > 0 || pile3 > 0) {
            // gets player 1's inputs as a string which is used to check if user input is a string
            while (!(stringPileNumber.matches("[1-3]") && stringPileNumber.length() > 0)) {
                System.out.print("Player 1: choose a pile: ");
                stringPileNumber = in.next();
            } pileNumber = Integer.parseInt(stringPileNumber);
            //checks if in any instance a user writes anything other than the variables it should and repeats
            switch (pileNumber) {
                case 1:
                case 2:
                case THREE:
                    while (((pile1 < 1 && stringPileNumber.matches("1"))
                            || (pile2 < 1 && stringPileNumber.matches("2"))
                            || (pile3 < 1 && stringPileNumber.matches("3")))
                            || !(stringPileNumber.matches("[1-3]+"))) {
                        System.out.print("Player 1: choose a pile: ");
                        stringPileNumber = in.next();
                    } pileNumber = Integer.parseInt(stringPileNumber);
                    break;
                default:
                    break;
            } // stores the amount of coins so we can keep track of how many are in one pile
            switch (pileNumber) {
                case 1:
                    amount = pile1;
                    break;
                case 2:
                    amount = pile2;
                    break;
                case THREE:
                    amount = pile3;
                    break;
                default:
                    break;
            } //checks if input for removing coins is a square number and is a valid input
            int player1Input = 0;
            double checkSqr = 1;
            while (player1Input > amount || checkSqr != 0 || player1Input <= 0) {
                do {
                    System.out.print("Now choose a square number of coins: ");
                    stringPlayer1Input = in.next();
                } while (!(stringPlayer1Input.matches("[1-9]+")));
                player1Input = Integer.parseInt(stringPlayer1Input);
                checkSqr = player1Input;
                checkSqr = Math.sqrt(checkSqr);
                checkSqr -= Math.floor(checkSqr);
                // assigns a "winner" value to determines who wins to at the end print out the winner
                winner = 1;
            } // this decides which pile to remove the coins from based on user input
            switch (pileNumber) {
                case 1:
                    pile1 = pile1 - player1Input;
                    break;
                case 2:
                    pile2 = pile2 - player1Input;
                    break;
                case THREE:
                    pile3 = pile3 - player1Input;
                    break;
                default:
                    break;
            } // makes sure that coins are in the pile before subtracting
            if (pile1 > 0 || pile2 > 0 || pile3 > 0) {
                System.out.println("Remaining coins: " + pile1 + ", " + pile2 + ", " + pile3);
            }
            if (pile1 > 0 || pile2 > 0 || pile3 > 0) {
                while (!(stringPile2Number.matches("[1-3]") && stringPile2Number.length() > 0)) {
                    System.out.print("Player 2: choose a pile: ");
                    stringPile2Number = in.next();
                    //makes sure user has put in a valid input
                }
                pile2Number = Integer.parseInt(stringPile2Number);
                // gets player 2's inputs
                switch (pile2Number) {
                    case 1:
                    case 2:
                    case THREE:
                        while ((pile1 < 1 && stringPile2Number.matches("1"))
                                || (pile2 < 1 && stringPile2Number.matches("2"))
                                || (pile3 < 1 && stringPile2Number.matches("3"))) {
                            System.out.print("Player 2: choose a pile: ");
                            stringPile2Number = in.next();
                        }break;
                    default:
                        break;
                }
                switch (pile2Number) {
                    case 1:
                        amount = pile1;
                        break;
                    case 2:
                        amount = pile2;
                        break;
                    case THREE:
                        amount = pile3;
                        break;
                    default:
                        break;
                } double checkSqr2 = 1; //does same as player 1 part of code
                while (checkSqr2 != 0 || player2Input <= 0 || amount < player2Input) {
                    do {
                        System.out.print("Now choose a square number of coins: ");
                        stringPlayer2Input = in.next();
                    } while (!(stringPlayer2Input.matches("[1-9]+")));
                    player2Input = Integer.parseInt(stringPlayer2Input);
                    checkSqr2 = player2Input;
                    checkSqr2 = Math.sqrt(checkSqr2);
                    checkSqr2 -= Math.floor(checkSqr2);
                } winner = 2;
                switch (pile2Number) {
                    case 1:
                        pile1 = pile1 - player2Input;
                        break;
                    case 2:
                        pile2 = pile2 - player2Input;
                        break;
                    case THREE:
                        pile3 = pile3 - player2Input;
                        break;
                    default:
                        break;
                }
            }// makes value refresh so it doesn't carry over unwanted values in next loop
            stringPileNumber = " ";
            stringPile2Number = " ";
            //prints remaining coins
            if (pile1 > 0 || pile2 > 0 || pile3 > 0) {
                System.out.println("Remaining coins: " + pile1 + ", " + pile2 + ", " + pile3);
            }// prints winner
        } System.out.println("Player " + winner + " wins!");
    }
}
