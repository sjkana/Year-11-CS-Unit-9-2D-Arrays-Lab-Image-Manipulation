package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        rotateImage("cyberpunk2077.jpg");

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage im = new APImage(pathOfFile);

        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Pixel pixel = im.getPixel(i, j);

                int average = getAverageColour(pixel);

                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);

                im.setPixel(i, j, pixel);
            }
        }
        im.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int blue = pixel.getBlue();
        int green = pixel.getGreen();
        return (red + blue + green) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage im = new APImage(pathOfFile);

        for (int i = 0; i < im.getWidth(); i++) {
            for (int j = 0; j < im.getHeight(); j++) {
                Pixel pixel = im.getPixel(i, j);

                int average = getAverageColour(pixel);
                if(average < 128)
                {
                    pixel.setRed(0);
                    pixel.setBlue(0);
                    pixel.setGreen(0);
                }
                else {
                    pixel.setRed(255);
                    pixel.setBlue(255);
                    pixel.setGreen(255);
                }

                im.setPixel(i, j, pixel);
            }
        }
        im.draw();

    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage im = new APImage(pathToFile);

        for (int i = 1; i < im.getWidth()-1; i++) {
            for (int j = 1; j < im.getHeight()-1; j++) {

                Pixel pixel = im.getPixel(i, j);
                int average = getAverageColour(pixel);

                Pixel pixelLeft = im.getPixel(i-1, j);
                int averageLeft = getAverageColour(pixelLeft);

                Pixel pixelDown = im.getPixel(i, j+1);
                int averageDown = getAverageColour(pixelDown);

                if(Math.abs(average - averageLeft)>threshold||Math.abs(average - averageDown) > threshold)
                {
                    pixel.setRed(0);
                    pixel.setBlue(0);
                    pixel.setGreen(0);
                }
                else
                {
                    pixel.setRed(255);
                    pixel.setBlue(255);
                    pixel.setGreen(255);
                }

                im.setPixel(i, j, pixel);

            }
        }
        im.draw();

    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage im = new APImage(pathToFile);
        int width = im.getWidth();
        int height = im.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                Pixel leftPixel = im.getPixel(x, y);
                Pixel rightPixel = im.getPixel(width - 1 - x, y);

                im.setPixel(x, y, rightPixel);
                im.setPixel(width - 1 - x, y, leftPixel);
            }
        }
        im.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage originalImage = new APImage(pathToFile);
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        APImage rotatedImage = new APImage(originalHeight, originalWidth);

        for (int x = 0; x < originalWidth; x++) {
            for (int y = 0; y < originalHeight; y++) {
                Pixel pixel = originalImage.getPixel(x, y);

                rotatedImage.setPixel(originalHeight - 1 - y, x, pixel);
            }
        }
        rotatedImage.draw();
    }

}
