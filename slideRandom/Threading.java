package teamProject.slideRandom;

public class Threading extends Thread {
    private PuzzleFrame puzzleFrame;
    public Threading(PuzzleFrame f) {
        puzzleFrame = f;
    }
    public void run() {
        puzzleFrame.time();
    }
}
