package teamProject.slideRandom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;

    public PuzzleButton(SlidePuzzleBoard b, PuzzleFrame f) {
        board = b;
        frame = f;
        setBackground(Color.white);
        setPreferredSize(new Dimension(80,80));
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        int index;
        if (getText().equals("") || getText().equals("Done"))
            index = 16;
        else{
            index = Integer.parseInt(getText());
            if (board.move(index)) {
                frame.update();
                frame.pMove();
            }
        }
    }
}
