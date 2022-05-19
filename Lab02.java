import javafx.util.Pair;

public class Lab02 {
	//nonogram method solves the nonogram with the given two parameters
	  //@param double array of integers that represent the columns
	  //@param double array of integers that represent the rows
	  //@returns double boolean array of solution
	private static boolean solveNonogram(boolean[][] nonogram, int[][] rows, int[][] cols, int row, int col) {
        if(row == nonogram.length) 
        	return true;
        else {
	        nonogram[row][col] = true;
	        if(!isSafe(nonogram[row], rows[row], col)) 
	        	return false;
	        boolean[] tempRowss = new boolean[rows.length];
	        for(int i = 0; i < rows.length; i++) {
	        	tempRowss[i] = nonogram[i][col];
	        } 
	        
	        int tempcol = col + 1;
            int temprows = row + (tempcol / nonogram[0].length);
            tempcol = tempcol % nonogram[0].length;
	            if(solveNonogram(nonogram, rows, cols, temprows, tempcol)) {
	                    return true; // return true if solvenonogram method returns true then return true;
	            }
	        
	        nonogram[row][col] = false;
	        if(!isSafe(nonogram[row], rows[row], col)) 
	        		return false;
	        tempRowss = new boolean[rows.length];
	        for(int i = 0; i < rows.length; i++) {
	                tempRowss[i] = nonogram[i][col];
	        }
	        
	        tempcol = col + 1;
	        temprows = row + (tempcol / nonogram[0].length);
	        tempcol = tempcol % nonogram[0].length;
	        if(solveNonogram(nonogram, rows, cols, temprows, tempcol)) {
	                return true; 
	        }
	        
	        return false;
        }
} 
        private static boolean isSafe(boolean[] rows, int[] key, int cols) {
                int compare = 0;
                int pattern = 0;
                boolean end = false;
                for(int i = 0; i <= cols; i++) {//looping through the row
	                if(rows[i]) {
	                	pattern++;
	                    if(!end) { 
	                    	if(compare >= key.length) { 
	                    		return false;
	                        }
	                    }
	                end = true;
	                }
                    else {
                    	if(end) {
                    		if(key[compare] != pattern) {
                    			return false;
                            }
                        pattern = 0;
                        compare++;
                        }
                    end = false;
                    }
                }
                if(cols == (rows.length - 1)) {
                	if(end) {
                		return compare == (key.length - 1) && pattern == key[compare];
                    }
                	else {
                		return compare == key.length;
                    }
                }
                else {
                	if(end) {
                		return pattern <= key[compare];
                	}
                }
                return true;
        }
        private static Pair<int[][], int[][]> scan(int[][] rows, int[][] cols){
            int rowSum = 0;
            for(int i = 0; i < rows.length; i++) {
                    for(int j = 0; j < rows[i].length; j++) {
                            rowSum += rows[i][j];
                    }
            }
            int colSum = 0;
            for(int i = 0; i < cols.length; i++) {
                    for(int j = 0; j < cols[i].length; j++) {
                            colSum += cols[i][j];
                    }
            }
            return new Pair<int[][], int[][]>(rows, cols);
    }
        
        public static void main(String[] args) {    
        	//initializing the arrays of nonogram puzzle
            int[][] row2 = 
            	{{0,2}, {2,1}, {0,4}, {0,3}, {0,1}};
            int[][] col2 = 
            	{{0,4}, {0,4}, {0,3}, {0,1}, {0,1}};
            Pair<int[][], int[][]> requirements = scan(row2, col2); //create new object
            int[][] rows = requirements.getKey(); //populating array with pair key and value
            int[][] cols = requirements.getValue();
            boolean[][] nonogram = new boolean[rows.length][cols.length];
            for(int row = 0; row < nonogram.length; row++) {
                for(int col = 0; col < nonogram[row].length; col++) {
                        if(nonogram[row][col]) 
                        	System.out.print("true, ");
                        else System.out.print("false, ");
                }
                System.out.println();
        }    }
}