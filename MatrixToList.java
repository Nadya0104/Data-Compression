/**
 * This class convert a 3D matrix to list and convert a list to 3D matrix
 */

import java.util.ArrayList;
import java.util.List;

public class MatrixToList {
    /**
     * Function that convert a 3D matrix to list.
     * @param Matrix is the 3D matrix.
     * @return the list.
     * */
    public static List<Integer> convertMatricesToList(int[][][] Matrix) {
        List<Integer> list = new ArrayList<>();
        for (int channel = 0; channel < 3; channel++) {
            for (int y = 0; y < Matrix[channel].length; y++) {
                for (int x = 0; x < Matrix[channel][y].length; x++) {
                    list.add(Matrix[channel][y][x]); // Add the current pixel value to the list
                }
            }
        }
        return list;
    }

    /**
     * Function that convert a list to 3D matrix.
     * @param data is the data list.
     * @param rows is the matrix rows.
     * @param columns is the matrix columns.
     * @return the 3D matrix.
     * */
    public static int [][][] convertListToMatrix(List<Integer> data, int rows, int columns){
        int[][][] matrix = new int[3][rows][columns];
        for (int i = 0, index = 0; i < 3; i++) {
            for (int y = 0; y < matrix[0].length; y++) {
                for (int x = 0; x < matrix[0][0].length; x++, index++) {
                    matrix[i][y][x] = data.get(index); // Assign the value from the list to the corresponding position in the matrix
                }
            }
        }
        return matrix;
    }
}
