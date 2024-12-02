package teamProject.slideRandom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class StartButton extends JButton implements ActionListener {
    SlidePuzzleBoard board;
    PuzzleFrame frame;
    private int t;
    private int min;

    public StartButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Start");
        board = b;
        frame = f;
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        frame.started();
        board.createPuzzleBoard();
        frame.update();
        t = LocalTime.now().getMinute()*60 + LocalTime.now().getSecond();
    }

    public int getT() {
        return t;
    }
}
