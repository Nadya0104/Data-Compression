/**
 * This class represent BMP image compression and decompression with Delta and Huffman algorithms
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class Compression {
    private static final String compressedPath =  "src\\compressed.txt"; // Path of the compressed file location

    /**
     * Function that compress the BMP image to a text file
     * @param imgPath is the image path location
     * */
    public static void compress(String imgPath) throws Exception{
        BufferedImage image = ImageIO.read(new File(imgPath)); // Load the image from the provided file path
        int [][][] imgMatrix = ImageToMatrix.convertBMPToMatrix(image); // Convert the image to a 3D matrix (RGB format)
        DeltaEncoder.deltaEncode(imgMatrix); // Apply delta encoding to the matrix
        List<Integer> dataList = MatrixToList.convertMatricesToList(imgMatrix); // Convert the 3D matrix to a list of integers
        HuffmanNode root = Huffman.buildHuffmanTree(dataList); // Build the Huffman tree based on the data list
        String compressedData = Huffman.compress(dataList); // Compress the data list into a binary string using Huffman encoding
        byte[] binaryData = ConvertToBinary.convertToBinary(compressedData); // Convert the binary string to a byte array
        // Write the binary data, Huffman tree, and image dimensions to a file
        FileOutputStream fileOut = new FileOutputStream(compressedPath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(binaryData);
        out.writeObject(root);
        out.writeObject(imgMatrix[0].length);
        out.writeObject(imgMatrix[0][0].length);
        System.out.println("Image compressed successfully! the compressed file path :" + compressedPath);
    }

    /**
     * Function that decompress the file to a BMP image
     * */
    public static void decompress() throws Exception{
        // Open the file and read the compressed data, Huffman tree, and image dimensions
        FileInputStream fileIn = new FileInputStream(compressedPath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        byte[] Data = (byte[]) in.readObject();
        HuffmanNode Root = (HuffmanNode) in.readObject();
        int rows = (int) in.readObject();
        int columns = (int) in.readObject();
        String data = ConvertToBinary.convertToString(Data); // Convert the binary data back to a string
        List<Integer> decompressedData = Huffman.decompress(data, Root); // Decompress the binary string using the Huffman tree
        int [][][] decompressedRGBMatrix = MatrixToList.convertListToMatrix(decompressedData, rows, columns); // Convert the decompressed list of integers back to a 3D matrix
        DeltaEncoder.deltaDecode(decompressedRGBMatrix); // Apply delta decoding to reconstruct the original matrix values
        BufferedImage decompressedImage = ImageToMatrix.rebuildImageFromMatrix(decompressedRGBMatrix); // Rebuild the image from the decompressed and decoded matrix
        ImageIO.write(decompressedImage, "bmp", new File("src\\output.bmp")); // Save the decompressed image as a BMP file
        System.out.println("Image decompressed successfully! decompressed image path : src\\output.bmp");
        // Close the input streams
        in.close();
        fileIn.close();
    }
}
