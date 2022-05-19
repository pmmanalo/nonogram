

import java.util.ArrayList;
import java.util.Arrays;

public class Lab02 {

    private static boolean isFound = false;

    //nonogram method solves the puzzle with the given two parameters
    //@param double array of integers that represent the columns
    //@param double array of integers that represent the rows
    //@returns double boolean array of solution
    public static boolean[][] solveNonogram(int[][] columns, int[][] rows) {

        boolean[][] result = new boolean[rows.length][columns.length];
        solve(columns, rows, result, 0, 0, 0);
        return result;
    }

    /**
     * Helper recursive method to solve the nonogram
     *
     * @param columns columns clues
     * @param rows rows clues
     * @param result result to match
     * @param row row position
     * @param col column position
     * @param d index in clue array
     */
    private static void solve(int[][] columns, int[][] rows, boolean[][] result, int row, int col, int d) {
        if (row >= rows.length) {
            if (isComplete(columns, rows, result)) {
                isFound = true;
            }
            return;
        }

        if (d == rows[row].length) {
            solve(columns, rows, result, row + 1, 0, 0);
        } else {

            if (col >= columns.length) {
                return;
            }

            int blockSize = rows[row][d];

            int extra = blockSize > 0 ? 1 : 0;
            for (int i = col; !isFound && i <=columns.length-blockSize; i++) {
                insertBlock(result, row, i, blockSize);
                solve(columns, rows, result, row, i + extra + blockSize, d + 1);
                if (!isFound) {
                    removeBlock(result, row, i, blockSize);
                }
            }
        }

    }

    /**
     * Helper method to check if the result array match all the clues.
     *
     * @param columns columns clues
     * @param rows rows clues
     * @param result result to match
     * @return true if match false otherwise
     */
    private static boolean isComplete(int[][] columns, int[][] rows, boolean[][] result) {
        return isRowsComplete(columns, rows, result) && isColumnsComplete(columns, rows, result);
    }


    /**
     * Helper method to check if the result array match the row clues.
     *
     * @param columns columns clues
     * @param rows rows clues
     * @param result result to match
     * @return true if match false otherwise
     */
    private static boolean isRowsComplete(int[][] columns, int[][] rows, boolean[][] result) {
        for (int i = 0; i < rows.length; i++) {
            StringBuilder rowStr = new StringBuilder();
            for (int j = 0; j < columns.length; j++) {
                if (result[i][j]) {
                    rowStr.append("x");
                } else {
                    rowStr.append(" ");
                }
            }

            String[] blockArray = rowStr.toString().trim().split("\\s+");

            ArrayList<Integer> actualBlocks = new ArrayList<>();
            for (int j = 0; j < rows[i].length; j++) {
                if (rows[i][j] != 0) {
                    actualBlocks.add(rows[i][j]);
                }
            }

            if (blockArray.length != actualBlocks.size()) {
                return false;
            } else {
                for (int j = 0; j < actualBlocks.size(); j++) {
                    if (actualBlocks.get(j) != blockArray[j].length()) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    /**
     * Helper method to check if the result array match the columns clues.
     *
     * @param columns columns clues
     * @param rows rows clues
     * @param result result to match
     * @return true if match false otherwise
     */
    private static boolean isColumnsComplete(int[][] columns, int[][] rows, boolean[][] result) {
        for (int i = 0; i < columns.length; i++) {
            StringBuilder colStr = new StringBuilder();
            for (int j = 0; j < rows.length; j++) {
                if (result[j][i]) {
                    colStr.append("x");
                } else {
                    colStr.append(" ");
                }
            }

            String[] blockArray = colStr.toString().trim().split("\\s+");

            ArrayList<Integer> actualBlocks = new ArrayList<>();
            for (int j = 0; j < columns[i].length; j++) {
                if (columns[i][j] != 0) {
                    actualBlocks.add(columns[i][j]);
                }
            }

            if (blockArray.length != actualBlocks.size()) {
                return false;
            } else {
                for (int j = 0; j < actualBlocks.size(); j++) {
                    if (actualBlocks.get(j) != blockArray[j].length()) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    /**
     * Helper method to mark a block in result array
     * of specified size starting form col'th columns in row'th row.
     *
     * @param result result array
     * @param row row position
     * @param col column position
     * @param blockSize specified block size
     */
    private static void insertBlock(boolean[][] result, int row, int col, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            result[row][col+i] = true;
        }
    }

    /**
     * Helper method to remove a block mark from result array
     * of specified size starting form col'th columns in row'th row.
     *
     * @param result result array
     * @param row row position
     * @param col column position
     * @param blockSize specified block size
     */
    private static void removeBlock(boolean[][] result, int row, int col, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            result[row][col+i] = false;
        }
    }

    public static void main(String[] args) {
        int[][] columns =  {{1,1}, {2,1}, {0,2}, {1,1}, {1,1}};
        int[][] rows =  {{1,1}, {0,1}, {0,5}, {0,1}, {1,1}};

        boolean[][] result = solveNonogram(columns, rows);

        for (boolean[] booleans : result) {
            System.out.println(Arrays.toString(booleans));
        }
    }
}
