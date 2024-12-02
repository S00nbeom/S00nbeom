package teamProject.slideRandom;

public class PuzzleStarter {

    public static void main(String[] args) {
        SlidePuzzleBoard board = new SlidePuzzleBoard(4);
        PuzzleFrame f = new PuzzleFrame(board);
        f.update();
        Threading t = new Threading(f);
        t.start();
    }
}
