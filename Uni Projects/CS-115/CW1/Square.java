

/**
 * Square.java
 * @version 2.0.0
 * Originally written by Bette Bultena but heavily modified for the purposes of
 * CSC-115 (Daniel Archambault and Liam OReilly)
 * was originally Oval but modified to fit characteristics of Square
 */

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * Square is a square shape that can be drawn to the screen, either
 * filled with colour or opaque.
 * Its position is determined by the upper left corner of the
 * square's bounding rectangle
 */
public class Square extends ClosedShape {
    //The width and height of the square (major and minor axis)
    private int side;

    /**
     * Creates a Square.
     * @param x The display component's x position.
     * @param y The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param colour The line colour or fill colour.
     * @param isFilled True if the oval is filled with colour, false if opaque.
     */
    public Square (int insertionTime, int x, int y, int vx, int vy, int side, Color colour, boolean isFilled) {
        super (insertionTime, x, y, vx, vy, colour, isFilled);
        this.side = side;
    }

    /**
     * Method to convert an Square to a string.
     */
    public String toString () {
        String result = "This is a square\n";
        result += super.toString ();
        result += "Its side is " + this.side;
        return result;
    }

    /**
     * @param width Resets the width.
     */
    public void setSide (int width) {
        this.side = width;
    }

    /**
     * @return The width of the Square.
     */
    public int getWidth() {
        return side;
    }

    /**
     * @return The height of the Square.
     */
    public int getHeight() {
        return side;
    }

    public int getSide() {
        return side;
    }

    /**
     * Draw the Square.
     * @param g The graphics object of the drawable component.
     */
    public void draw (GraphicsContext g) {
        g.setFill (colour);
        g.setStroke( colour );
        if (isFilled) {
            g.fillRect( x, y, side, side );
        }
        else {
            g.strokeRect( x, y, side, side );
        }
    }
}
