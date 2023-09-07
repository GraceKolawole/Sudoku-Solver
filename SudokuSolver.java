public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        printBoard(board);

        if(solveBoard(board)){
            System.out.println("Puzzle successfully solved");
        }
        else{
            System.out.println("Puzzle cannot be solved");
        }
        printBoard(board);

    }

    private static void printBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean checkNumInRow(int[][] board, int number, int row){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[row][i]==number){
                return true;
            }
        }
        return false;
    }
    private static boolean checkNumInColumn(int[][] board, int number, int column){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[i][column]==number){
                return true;
            }
        }
        return false;
    }

    private static boolean checkNumInBox(int[][] board, int number, int row, int column){
        int localRowBox = row - row % 3;
        int localColumnBox = column - column % 3;

        for(int i = localRowBox; i < localRowBox + 3; i++){
            for(int j = localColumnBox; j < localColumnBox + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPosition(int[][] board, int number, int row, int column){
        return !checkNumInRow(board, number, row) &&
                !checkNumInColumn(board, number, column) &&
                !checkNumInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board){
        for(int row=0; row<GRID_SIZE; row++){
            for(int column=0; column<GRID_SIZE; column++){
                if(board[row][column]==0){
                    for(int possibleNum =1; possibleNum<=GRID_SIZE; possibleNum++){
                        if(isValidPosition(board, possibleNum, row, column)){
                            board[row][column] = possibleNum;

                            if(solveBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }

        }
        return  true;
    }

}