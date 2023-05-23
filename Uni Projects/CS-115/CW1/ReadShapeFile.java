
/**
 * This class reads a shape file.  For the format of this shape file, see the assignment description.
 * Also, please see the shape files ExampleShapes.txt, ExampleShapesStill.txt, and TwoRedCircles.txt
 *
 * @author you
 *
 */

import javafx.scene.paint.Color;
import java.io.*;
import java.util.Scanner;

public class ReadShapeFile {

    // TODO: You will likely need to write four methods here. One method to
    // construct each shape
    // given the Scanner passed as a parameter. I would suggest static
    // methods in this case.

    /**
     * Reads the data file used by the program and returns the constructed queue
     *
     * @param in
     *            the scanner of the file
     * @return the queue represented by the data file
     */
    private static Queue<ClosedShape> readLineByLine(Scanner in) {
        Queue<ClosedShape> shapeQueue = new Queue<ClosedShape>();

        while (in.hasNextLine()) {
            String nextLine = in.nextLine();
            Scanner scan = new Scanner(nextLine);
            String name = scan.next();
            if (name.equals("oval")){
                shapeQueue.enqueue(readOval(scan));
            } else if (name.equals("circle")) {
                shapeQueue.enqueue(readCircle(scan));
            } else if (name.equals("square")) {
                shapeQueue.enqueue(readSquare(scan));
            } else if (name.matches("rect")) {
                shapeQueue.enqueue(readRectangle(scan));
            } else if (name.matches("triangle")){
                shapeQueue.enqueue((readTriangle(scan)));
            }
        }

        //read in the shape files and place them on the Queue

        //Right now, returning an empty Queue.  You need to change this.
        return shapeQueue;
    }
    /**
     * initialises values for Triangle
     * stores it in the object readTriangle
     * @param in
     *            the scanner of the file
     * @return readTriangle
     */
    private static Triangle readTriangle(Scanner in){
        int x = in.nextInt();
        int y = in.nextInt();
        int vx = in.nextInt();
        int vy = in.nextInt();
        boolean isFilled = in.nextBoolean();
        int width = in.nextInt();
        int height = in.nextInt();
        int R = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int insertionTime = in.nextInt();
        Color color = Color.rgb(R, G, B);
        Triangle readTriangle = new Triangle(insertionTime, x, y, vx, vy, width, height, color, isFilled);
        return readTriangle;
    }
    /**
     * initialises values for Oval
     * stores it in the object readOval
     * @param in
     *            the scanner of the file
     * @return readOval
     */
    private static Oval readOval(Scanner in){
        int x = in.nextInt();
        int y = in.nextInt();
        int vx = in.nextInt();
        int vy = in.nextInt();
        boolean isFilled = in.nextBoolean();
        int width = in.nextInt();
        int height = in.nextInt();
        int R = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int insertionTime = in.nextInt();
        Color color = Color.rgb(R, G, B);
        Oval readOval = new Oval(insertionTime, x, y, vx, vy, width, height, color, isFilled);
        return readOval;
    }
    /**
     * initialises values for rectangle
     * stores it in the object readRectangle
     * @param in
     *            the scanner of the file
     * @return readRectangle
     */
    private static Rect readRectangle(Scanner in){
        int x = in.nextInt();
        int y = in.nextInt();
        int vx = in.nextInt();
        int vy = in.nextInt();
        boolean isFilled = in.nextBoolean();
        int width = in.nextInt();
        int height = in.nextInt();
        int R = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int insertionTime = in.nextInt();
        Color color = Color.rgb(R, G, B);
        Rect readRectangle = new Rect(insertionTime, x, y, vx, vy, width, height, color, isFilled);
        return readRectangle;
    }
    /**
     * initialises values for Square
     * stores it in the object readSquare
     * @param in
     *            the scanner of the file
     * @return readSquare
     */
    private static Square readSquare(Scanner in){
        int x = in.nextInt();
        int y = in.nextInt();
        int vx = in.nextInt();
        int vy = in.nextInt();
        boolean isFilled = in.nextBoolean();
        int side = in.nextInt();
        int R = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int insertionTime = in.nextInt();
        Color color = Color.rgb(R, G, B);
        Square readSquare = new Square(insertionTime, x, y, vx, vy, side, color, isFilled);
        return readSquare;
    }
    /**
     * initialises values for Circle
     * stores it in the object readCircle
     * @param in
     *            the scanner of the file
     * @return readCircle
     */
    private static Circle readCircle(Scanner in){
        int x = in.nextInt();
        int y = in.nextInt();
        int vx = in.nextInt();
        int vy = in.nextInt();
        boolean isFilled = in.nextBoolean();
        int diameter = in.nextInt();
        int R = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int insertionTime = in.nextInt();
        Color color = Color.rgb(R, G, B);
        Circle readCircle = new Circle(insertionTime, x, y, vx, vy, diameter, color, isFilled);
        return readCircle;
    }





    /**
     * Method to read the file and return a queue of shapes from this file. The
     * program should handle the file not found exception here and shut down the
     * program gracefully.
     *
     * @param filename
     *            the name of the file
     * @return the queue of shapes from the file
     */
    public static Queue<ClosedShape> readDataFile(String filename) {
        // HINT: You might want to open a file here.
        File inputFile = new File (filename);
        try {
            Scanner in = new Scanner(inputFile);
            return ReadShapeFile.readLineByLine(in);
        } catch (FileNotFoundException e) {
            System.out.println ("Cannot open " + filename);
            System.exit (-1);
        }
        return null;
    }


}
