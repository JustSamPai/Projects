import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The type File reader.
 * this class is a file reader which takes information
 * from a text file
 */
public class FileReader {

    /**
     * Read profile set bst.
     *
     * @param filename the filename
     * @throws FileNotFoundException
     * @return the bst
     */
    public static BST readProfileSet (String filename) throws FileNotFoundException{
        File fileName = new File(filename);
        try {
            Scanner in = new Scanner(fileName);
            return FileReader.readLineByLine(in);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            System.exit(0);
        }
        return null;
    }



    /**
     /**
     * Method to read the file and return a queue of shapes from this file. The
     * program should handle the file not found exception here and shut down the
     * program gracefully.
     *
     * @param in
     *            the name of the file
     * @return the queue of shapes from the file
     */
    private static Profile readProfile(Scanner in){
        in.useDelimiter(",|\n");
        String lastName = in.next();
        String firstName = in.next();
        int day = in.nextInt();
        int month = in.nextInt();
        int year = in.nextInt();
        String email = in.next();
        String interestsString = in.next();
        String activitiesString = in.next();
        String[] interests = interestsString.split(";");
        String[] activities =  activitiesString.split(";");
        Profile profile = new Profile(firstName, lastName, day, month, year, email,interests,activities);


        return profile;
    }



    /**
     * Method to read the file and return information about people from this file.
     *
     * @param in
     *            the name of the file
     * @return the queue of shapes from the file
     */
    private static BST readLineByLine(Scanner in){
        BST tree = new BST();

        while(in.hasNext()){
            tree.addProfile(readProfile(in));
        }
        return tree;
    }


}
