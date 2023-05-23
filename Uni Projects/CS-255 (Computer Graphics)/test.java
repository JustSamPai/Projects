package com.example.cw1;


/*
CS-255 Getting started code for the assignment
I do not give you permission to post this code online
Do not post your solution online
Do not copy code
Do not use JavaFX functions or other libraries to do the main parts of the assignment:
	1. Creating a resized image (you must implement nearest neighbour and bilinear interpolation yourself
	2. Gamma correcting the image
	3. Creating the image which has all the thumbnails and event handling to change the larger image
All of those functions must be written by yourself
You may use libraries / IDE to achieve a better GUI
*/
import java.io.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
//import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static java.lang.Math.round;
//import javafx.scene.layout.TilePane;


public class test extends Application {
    final static int SIZE = 40;
    short cthead[][][]; //store the 3D volume data set
    float grey[][][]; //store the 3D volume data set converted to 0-1 ready to copy to the image
    short min, max; //min/max value in the 3D volume data set
    ImageView TopView;

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        stage.setTitle("CThead Viewer");

        try {
            ReadData();
        } catch (IOException e) {
            System.out.println("Error: The CThead file is not in the working directory");
            System.out.println("Working Directory = " + System.getProperty("CThead"));
            return;
        }

        //int width=1024, height=1024; //maximum size of the image
        //We need 3 things to see an image
        //1. We need to create the image
        Image top_image=GetSlice(); //go get the slice image
        //2. We create a view of that image
        TopView = new ImageView(top_image); //and then see 3. below

        //Create the simple GUI
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Nearest neighbour");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Bilinear");
        rb2.setToggleGroup(group);

        Slider szslider = new Slider(32, 1024, 256);

        Slider gamma_slider = new Slider(.1, 4, 1);

        //Radio button changes between nearest neighbour and bilinear
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

                if (rb1.isSelected()) {
                    System.out.println("Nearest Neighbor");
                } else if (rb2.isSelected()) {
                    System.out.println("Bilinear");
                }
            }
        });

        //Size of main image changes (slider)
        szslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number >
                                        observable, Number oldValue, Number newValue) {

                System.out.println(newValue.intValue());
                if (rb1.isSelected()) {
                    //Here's the basic code you need to update an image
                    Image updatedImage = TopView.getImage();
                    TopView.setImage(null); //clear the old image
                    updatedImage = RescaleNeighbour(updatedImage, newValue.intValue());
                    TopView.setImage(updatedImage); //Update the GUI so the new image is displayed
                }

                if (rb2.isSelected()) {
                    Image updatedImage = TopView.getImage();
                    TopView.setImage(null);
                    updatedImage = RescaleBilinear(updatedImage, newValue.intValue());
                    TopView.setImage(updatedImage);
                }

            }
        });




        //Gamma value changes
        gamma_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number >
                                        observable, Number oldValue, Number newValue) {

                System.out.println(newValue.doubleValue());
                Image updatedImage = TopView.getImage();
                TopView.setImage(null);
                updatedImage = ContrastAdjust(updatedImage, newValue.floatValue());
                TopView.setImage(updatedImage);
            }
        });

        VBox root = new VBox();

        //Add all the GUI elements
        //3. (referring to the 3 things we need to display an image)
        //we need to add it to the layout
        root.getChildren().addAll(rb1, rb2, gamma_slider,szslider, TopView);

        //Display to user
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();

        ThumbWindow(scene.getX()+200, scene.getY()+200);
    }



    //Function to read in the cthead data set
    public void ReadData() throws IOException {
        //File name is hardcoded here - much nicer to have a dialog to select it and capture the size from the user
        File file = new File("CThead");
        //Read the data quickly via a buffer (in C++ you can just do a single fread - I couldn't find the equivalent in Java)
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));



        int i, j, k; //loop through the 3D data set

        min=Short.MAX_VALUE; max=Short.MIN_VALUE; //set to extreme values
        short read; //value read in
        int b1, b2; //data is wrong Endian (check wikipedia) for Java so we need to swap the bytes around

        cthead = new short[113][256][256]; //allocate the memory - note this is fixed for this data set
        grey= new float[113][256][256];
        //loop through the data reading it in
        for (k=0; k<113; k++) {
            for (j=0; j<256; j++) {
                for (i=0; i<256; i++) {
                    //because the Endianess is wrong, it needs to be read byte at a time and swapped
                    b1=((int)in.readByte()) & 0xff; //the 0xff is because Java does not have unsigned types (C++ is so much easier!)
                    b2=((int)in.readByte()) & 0xff; //the 0xff is because Java does not have unsigned types (C++ is so much easier!)
                    read=(short)((b2<<8) | b1); //and swizzle the bytes around
                    if (read<min) min=read; //update the minimum
                    if (read>max) max=read; //update the maximum
                    cthead[k][j][i]=read; //put the short into memory (in C++ you can replace all this code with one fread)
                }
            }
        }
        System.out.println(min+" "+max); //diagnostic - for CThead this should be -1117, 2248
        //(i.e. there are 3366 levels of grey, and now we will normalise them to 0-1 for display purposes
        //I know the min and max already, so I could have put the normalisation in the above loop, but I put it separate here
        for (k=0; k<113; k++) {
            for (j=0; j<256; j++) {
                for (i=0; i<256; i++) {
                    grey[k][j][i]=((float) cthead[k][j][i]-(float) min)/((float) max-(float) min);
                }
            }
        }
    }
    // uses nearest neighbor to get pixel values
    public Image RescaleNeighbour(Image originalImage, int newSize) {
        WritableImage finalImage = new WritableImage(newSize, newSize);
        PixelWriter pixelWriter = finalImage.getPixelWriter();
        PixelReader pixelReader = originalImage.getPixelReader();
        for(int y = 0; y < (finalImage.getHeight() - 1); y++) {
            for(int x = 0; x < (finalImage.getWidth() - 1); x++) {
                int a = (int) (y * (originalImage.getHeight() / newSize));
                int b = (int) (x * (originalImage.getWidth() / newSize));
                a = (int) Math.floor(a);
                b = (int) Math.floor(b);
                Color color = pixelReader.getColor(b, a);
                pixelWriter.setColor(x, y, color);
            }
        }
        return finalImage;
    }

    // changes the images size and uses bilinear interpolation to change the pixel values
    public Image RescaleBilinear(Image originalImage, int newSize) {
        WritableImage finalImage = new WritableImage(newSize, newSize);
        PixelWriter pixelWriter = finalImage.getPixelWriter();
        PixelReader pixelReader = originalImage.getPixelReader();
        float ratioX;
        float ratioY;

        for(int y = 0; y < (finalImage.getHeight() - 1); y++) {
            for(int x = 0; x < (finalImage.getWidth() - 1); x++) {
                float a = (float) (y * (originalImage.getHeight() / newSize));
                float b = (float) (x * (originalImage.getWidth() / newSize));

                int floorA = (int) Math.floor(a);
                int ceilA = (int) Math.ceil(a);
                int floorB = (int) Math.floor(b);
                int ceilB = (int) Math.ceil(b);

                if ((floorA < 1 && floorB < 1) || ((a % 1 != 0) || (b % 1 != 0))) {
                    Color color = pixelReader.getColor((int) b,(int) a);
                    pixelWriter.setColor(x, y, color);
                } else {
                    ratioX = (b - floorB) / (ceilB - floorB);
                    ratioY = (a - ceilA) / (floorA - ceilA);
                    Color colorFloorV1 = pixelReader.getColor(floorB, floorA);
                    Color colorFloorV2 = pixelReader.getColor(ceilB, floorA);
                    Color colorFloorDifference = minusRGB(colorFloorV2, colorFloorV1);
                    Color colorFloorRatio = ratioRGB(colorFloorDifference, ratioX);
                    Color colorFloor = addRGB(colorFloorV1, colorFloorRatio);

                    Color colorCeilV1 = pixelReader.getColor(floorB, ceilA);
                    Color colorCeilV2 = pixelReader.getColor(ceilB, ceilA);
                    Color colorCeilDifference = minusRGB(colorCeilV2, colorCeilV1);
                    Color colorCeilRatio = ratioRGB(colorCeilDifference, ratioX);
                    Color colorCeil = addRGB(colorCeilV1, colorCeilRatio);

                    Color colorDifference = minusRGB(colorFloor, colorCeil);
                    Color colorRatio = ratioRGB(colorDifference, ratioY);
                    Color color = addRGB(colorCeil, colorRatio);
                    pixelWriter.setColor(x, y, color);
                }

            }
        }
        return finalImage;
    }
    //gets the ratio of RGB values where the min is the highest possible value
    public Color ratioRGB (Color color, float ratio) {
        int red = Math.min(255, (int) (color.getRed()) * (int) ratio);
        int green = Math.min(255, (int) (color.getGreen()) * (int) ratio);
        int blue = Math.min(255, (int) (color.getBlue()) * (int) ratio);
        return Color.color(red, green, blue);
    }
    // adds RGB values
    public Color addRGB (Color color1, Color color2) {
        int red = Math.min(255, (int) (color1.getRed()) + (int) (color2.getRed()));
        int green = Math.min(255, (int) (color1.getGreen()) + (int) (color2.getGreen()));
        int blue = Math.min(255, (int) (color1.getBlue()) + (int) (color2.getBlue()));
        return Color.color(red, blue, green);
    }
    //minus' RGB values
    public Color minusRGB (Color color1, Color color2) {
        int red = Math.max(0, (int) (color1.getRed()) - (int) (color2.getRed()));
        int green = Math.max(0, (int) (color1.getGreen()) - (int) (color2.getGreen()));
        int blue = Math.max(0, (int) (color1.getBlue()) - (int) (color2.getBlue()));
        return Color.color(red, blue, green);
    }

    //used to get gamma contrast
    public Image ContrastAdjust(Image image, float newValue) {
        ColorAdjust contrastAdjust = new ColorAdjust();
        contrastAdjust.setContrast(newValue);
        ImageView CTheadImageView = new ImageView(image);
        CTheadImageView.setEffect(contrastAdjust);
        return CTheadImageView.snapshot(null, null);
    }


    //Gets an image from slice 76
    public Image GetSlice() {
        WritableImage image = new WritableImage(256, 256);
        //Find the width and height of the image to be process
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        float val;

        //Get an interface to write to that image memory
        PixelWriter image_writer = image.getPixelWriter();

        //Iterate over all pixels
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //For each pixel, get the colour from the cthead slice 76
                val=grey[76][y][x];
                Color color=Color.color(val,val,val);

                //Apply the new colour
                image_writer.setColor(x, y, color);
            }
        }

        return image;
    }
    //this method is used to get all slices
    public Image GetAllSlice(int slice) {
        WritableImage image = new WritableImage(256, 256);
        //Find the width and height of the image to be process
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        float val;

        //Get an interface to write to that image memory
        PixelWriter image_writer = image.getPixelWriter();

        //Iterate over all pixels
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //For each pixel, get the colour from the cthead slice 76
                val=grey[slice][y][x];
                Color color=Color.color(val,val,val);

                //Apply the new colour
                image_writer.setColor(x, y, color);
            }
        }

        return image;
    }


    public void ThumbWindow(double atX, double atY) {
        GridPane ThumbLayout = new GridPane();


        ThumbLayout.setAlignment(Pos.TOP_CENTER);
        ThumbLayout.setHgap(0);
        ThumbLayout.setVgap(0);

        final int MAX_COLUMNS = 9;

        int x = 0;
        int y = 0;

        for (int i = 0; i < 113; i++) {
            ImageView imageView = new ImageView();
            Image image = GetAllSlice(i);
            imageView.setImage(image);
            imageView.setFitWidth(SIZE);
            imageView.setFitHeight(SIZE);
            imageView.setSmooth(true);

            ThumbLayout.add(imageView, x, y);
            if (x < MAX_COLUMNS) {
                x++;
            } else {
                x = 0;
                y++;
            }
        }

        //scene dimensions
        final int sceneWidth = 10 * SIZE;
        final int sceneHeight = 12 * SIZE;
        
        Scene ThumbScene = new Scene(ThumbLayout, sceneWidth, sceneHeight);

        //Add mouse over handler - the large image is change to the image the mouse is over
        ThumbLayout.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, event -> {
            System.out.println(event.getX()+"  "+event.getY());




            int column = (int) Math.ceil(event.getX() / SIZE);
            int row = (int) Math.ceil(event.getY() / SIZE);


            Image image;

            try {
                image = GetAllSlice(column + ((MAX_COLUMNS + 1) * (row - 1)));
                TopView.setImage(image);
            } catch (ArrayIndexOutOfBoundsException e) {
                image = GetAllSlice(112);
                TopView.setImage(image);
            }
            event.consume();
        });

        //Build and display the new window
        Stage newWindow = new Stage();
        newWindow.setTitle("CThead Slices");
        newWindow.setScene(ThumbScene);

        // Set position of second window, related to primary window.
        newWindow.setX(atX);
        newWindow.setY(atY);

        newWindow.show();
    }


    public static void main(String[] args) {

        launch();
    }

}
