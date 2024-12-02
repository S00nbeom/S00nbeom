package teamProject.slideRandom;

import java.util.Random;

//Model
public class SlidePuzzleBoard {
    private PuzzlePiece[][] board;
    private int er;
    private int ec;
    private int size;
    private boolean game_on = false;

    public SlidePuzzleBoard(int s) {
        size = s;
        er = size-1;
        ec = size-1;
        board = new PuzzlePiece[size][size];
    }
    PuzzlePiece getPuzzlePiece(int row, int col) {
        return board[row][col];
    }

    public void createPuzzleBoard()
    {
        int[] numbers = generateRandomPermutation(size*size-1);
        if(isPossible(numbers)) {
            int i = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (c != size-1 || r != size-1) {
                        board[r][c] = new PuzzlePiece(numbers[i] + 1);
                        i += 1;
                    } else {
                        board[size - 1][size - 1] = new PuzzlePiece(size * size);
                        er = size - 1;
                        ec = size - 1;
                    }
                }
            }
            game_on = true;
        }
        else{
            createPuzzleBoard();
        }
    }
    private int[] generateRandomPermutation(int n) {
        Random random = new Random();
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            int d = random.nextInt(i+1);
            permutation[i] = permutation[d];
            permutation[d] = i;
        }
        return permutation;
    }

    private boolean isPossible(int[] numbers){
        int reversal = 0;
        for (int i = 0; i < size*size-1; i++) {
            for (int j = i+1; j < size*size-1;j++){
                if (numbers[i] > numbers[j]){
                    reversal++;
                }
            }
        }
        System.out.println(reversal); // test
        return (reversal != 0 && reversal % 2 == 0);
    }


    boolean gameOn(){
        return game_on;
    }
    boolean gameOver(){
        if (er != size-1 || ec != size-1)
            return false;
        else{
            int number = 1;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (c != size-1 || r != size-1)
                        if (board[r][c].faceValue() != number)
                            return false;
                        else
                            number++;
                }
            }
            game_on = false;
            return true;
        }
    }

    boolean move(int w){
        PuzzlePiece temp = board[er][ec];
        if (er > 0 && found(w,er-1,ec)){
            board[er][ec] = board[er-1][ec];
            board[er-1][ec] = temp;
            er -= 1;
        }
        else if (er < size-1 && found(w,er+1,ec)){
            board[er][ec] = board[er+1][ec];
            board[er+1][ec] = temp;
            er += 1;
        }
        else if (ec > 0 && found(w,er,ec-1)){
            board[er][ec] = board[er][ec-1];
            board[er][ec-1] = temp;
            ec -= 1;
        }
        else if (ec < size-1 && found(w,er,ec+1)){
            board[er][ec] = board[er][ec+1];
            board[er][ec+1] = temp;
            ec += 1;
        }
        else
            return false;
        return true;
    }
    boolean found(int v, int row, int col){
        return (board[row][col].faceValue() == v);
    }

    boolean foundBlank(int w){
        PuzzlePiece temp = board[er][ec];
        if (er > 0 && found(w,er-1,ec)){
        }
        else if (er < size-1 && found(w,er+1,ec)){
        }
        else if (ec > 0 && found(w,er,ec-1)){
        }
        else if (ec < size-1 && found(w,er,ec+1)){
        }
        else
            return false;
        return true;
    }

    int getSize(){
        return size;
    }
}
