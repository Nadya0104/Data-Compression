/**
 * This class convert a String to byte array and a byte array to String
 */

public class ConvertToBinary {
    /**
     * Function that convert a String to byte array.
     * @param compressedData is the String.
     * @return the byte array.
     * */
    public static byte[] convertToBinary(String compressedData) {
        // Calculate the number of bytes needed to store the binary data
        int numOfBytes = (compressedData.length() + 7) / 8;
        byte[] binaryData = new byte[numOfBytes];
        for (int i = 0; i < compressedData.length(); i++) { // Iterate over each character in the compressed data string
            if (compressedData.charAt(i) == '1') { // Check if the current character is '1'
                int byteIndex = i / 8; // Determine the index of the byte in which the bit will be set
                int bitPosition = 7 - (i % 8);  // Determine the position of the bit within the byte (from the left)
                binaryData[byteIndex] |= 1 << bitPosition; // Set the appropriate bit in the byte using bitwise OR
            }
        }
        return binaryData;  // Return the byte array containing the binary representation of the compressed data
    }

    /**
     * Function that convert a byte array to String.
     * @param data is the data byte array.
     * @return the String.
     * */
    public static String convertToString(byte[] data){
        StringBuilder binaryStringBuilder = new StringBuilder(); // Initialize a StringBuilder to construct the binary string
        for (byte b : data) { // Iterate over each byte in the byte array
            for (int i = 0; i < 8; i++) { // Iterate over each bit in the byte
                binaryStringBuilder.append((b >> (7 - i)) & 1); // Extract the bit at the ith position and append it to the binary string
            }
        }
        return binaryStringBuilder.toString();  // Return the complete binary string representation of the byte array
    }
}
