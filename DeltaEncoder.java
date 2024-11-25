/**
 * This class represent Delta encoding and decoding algorithm
 */

public class DeltaEncoder {

    /**
     * Function that delta encoding a 3D matrix.
     * @param matrix is the matrix that need to encode.
     * */
    public static void deltaEncode(int[][][] matrix) {
        for (int channel = 0; channel < 3; channel++) {  // Loop through each color channel (3 channels: RGB)
            for (int y = 0; y < matrix[channel].length; y++) { // Loop through each row in the current channel
                for (int x = matrix[channel][y].length - 1; x > 0; x--) { // Loop through each column in the row, starting from the end
                    matrix[channel][y][x] -= matrix[channel][y][x - 1]; // Subtract the previous pixel's value from the current one
                }
            }
        }
    }

    /**
     * Function that delta decoding a 3D matrix.
     * @param encoded is the matrix that need to decode.
     * */
    public static void deltaDecode(int[][][] encoded) {
        for (int channel = 0; channel < 3; channel++) {  // Loop through each color channel (3 channels: RGB)
            for (int y = 0; y < encoded[channel].length; y++) { // Loop through each row in the current channel
                for (int x = 1; x < encoded[channel][y].length; x++) { // Loop through each column in the row, starting from the second
                   encoded[channel][y][x] += encoded[channel][y][x - 1]; // Add the previous pixel's value to the current one
                }
            }
        }
    }
}
