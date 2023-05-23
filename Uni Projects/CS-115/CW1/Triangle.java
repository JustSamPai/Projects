

/**
 * Triangle.java
 * @version 2.0.0
 * Originally written by Bette Bultena but heavily modified for the purposes of
 * CSC-115 (Daniel Archambault and Liam OReilly)
 * was originally Oval but modified to fit characteristics of Triangle
 */

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * Triangle is a triangle shape that can be drawn to the screen, either
 * filled with colour or opaque.
 * Its position is determined by the upper left corner of the
 * triangle's bounding rectangle
 */
public class Triangle extends ClosedShape {
    //The width and height of the oval (major and minor axis)
    private int width, height;

    /**
     * Creates an triangle.
     * @param x The display component's x position.
     * @param y The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param width The width of the rectangle (in pixels).
     * @param height The height of the rectangle (in pixels).
     * @param colour The line colour or fill colour.
     * @param isFilled True if the rectangle is filled with colour, false if opaque.
     */
    public Triangle (int insertionTime, int x, int y, int vx, int vy, int width, int height, Color colour, boolean isFilled) {
        super (insertionTime, x, y, vx, vy, colour, isFilled);
        this.width = width;
        this.height = height;
    }



    /**
     * Method to convert an rectangle to a string.
     */
    public String toString () {
        String result = "This is a triangle\n";
        result += super.toString ();
        result += "Its width is " + this.width + " and its height is " + this.height;
        return result;
    }


    /**
     * @param width Resets the width.
     */
    public void setWidth (int width) {
        this.width = width;
    }

    /**
     * @param height Resets the height.
     */
    public void setHeight (int height) {
        this.height = height;
    }

    /**
     * @return The width of the rectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the rectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Draw the Triangle.
     */
    public double[] getXCoords() {
        double[] xPoints = {(width / 2), width, 0};
        return xPoints;
    }

    public double[] getYCoords() {
        double[] yPoints = {0, height, height};
        return yPoints;
    }

    public void draw (GraphicsContext g) {
        g.setFill (colour);
        g.setStroke( colour );
        if (isFilled) {
            g.fillPolygon(getXCoords(), getYCoords(), 3);
        }
        else {
            g.strokePolygon(getXCoords(), getYCoords(), 3);
        }
    }


}
