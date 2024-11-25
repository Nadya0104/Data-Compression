/**
 * This class convert an image to a 3D pixel's matrix and convert a matrix to image
 */

import java.awt.image.BufferedImage;

public class ImageToMatrix {
    /**
     * Function that convert an image to 3D pixel's matrix.
     * @param image is the image that need to convert.
     * @return the 3D pixel's matrix
     * */
    public static int[][][] convertBMPToMatrix(BufferedImage image){
        int [][][] rgbMatrix = new int[3][image.getHeight()][image.getWidth()];  // 3 matrices for R, G, B
        // Loop through each pixel in the image by its coordinates (x, y)
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                rgbMatrix[0][y][x] = (pixel >> 16) & 0xFF; // Red
                rgbMatrix[1][y][x] = (pixel >> 8) & 0xFF;  // Green
                rgbMatrix[2][y][x] = pixel & 0xFF;         // Blue
            }
        }
        return rgbMatrix; // Return the 3D matrix containing separate R, G, and B values for each pixel
    }

    /**
     * Function that convert a 3D matrix to image.
     * @param rgbMatrix is the 3D matrix.
     * @return the rebuild image.
     * */
    public static BufferedImage rebuildImageFromMatrix(int[][][] rgbMatrix) {
        // Get the dimensions of the image from the matrix
        int width = rgbMatrix[0][0].length;
        int height = rgbMatrix[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Retrieve the R, G, and B values from the matrix
                int r = rgbMatrix[0][y][x];
                int g = rgbMatrix[1][y][x];
                int b = rgbMatrix[2][y][x];
                int pixel = (r << 16) | (g << 8) | b; // Combine the R, G, and B values into a single pixel value
                image.setRGB(x, y, pixel); // Set the pixel value in the BufferedImage
            }
        }
        return image;
    }
}
