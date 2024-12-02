package teamProject.slideRandom;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class PuzzleFrame extends JFrame {
    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;
    private StartButton sb;
    private int size;
    private boolean started = false;
    private boolean finished = false;
    private float dt = 10000000;
    private int cnt = 0;
    private JLabel label = new JLabel("time: 0s, move: 0");

    public PuzzleFrame(SlidePuzzleBoard b) {
        board = b;
        size = board.getSize();
        button_board = new PuzzleButton[size][size];
        sb = new StartButton(board,this);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(sb);
        p1.add(label);
        JPanel p2 = new JPanel(new GridLayout(size,size));

        for (int r = 0; r <size; r++) {
            for (int c = 0; c<size; c++) {
                button_board[r][c] = new PuzzleButton(board,this);
                p2.add(button_board[r][c]);
            }
        }
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);

        setTitle("PuzzleFrame");
        setVisible(true);
        setSize(size*100,size*100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }
    public void update(){
        if (board.gameOn()){
            for (int r = 0; r <size; r++) {
                for (int c = 0; c<size; c++) {
                    int piece = board.getPuzzlePiece(r,c).faceValue();
                    if (piece != size*size) {
                        button_board[r][c].setText(""+piece);
                        if (board.foundBlank(piece))
                            button_board[r][c].setBackground(Color.yellow);
                        else button_board[r][c].setBackground(Color.white);
                    }
                    else {
                        button_board[r][c].setText("");
                        button_board[r][c].setBackground(Color.black);
                    }
                }
            }
            if (board.gameOver())
                finish();
        }
    }
    public void finish(){
        button_board[size-1][size-1].setText("Done");
        finished = true;
        started = false;
    }
    public void started() {
        started = true;
        cnt = 0;
    }
    public void time(){
        while(true){
            ut(LocalTime.now());
        }
    }
    public void ut(LocalTime t) {
        int nowTime = t.getHour()*3600+t.getMinute() * 60 + t.getSecond();
        if(started && !finished)
            label.setText("time: "+(nowTime - sb.getT()) + "s, move: " + cnt);
        else if (finished){
            dt = (nowTime - sb.getT());
            System.out.println("Clear time : " + (dt * 0.7 + cnt * 0.3) );
            finished = false;
        }
    }

    public void pMove(){
        cnt++;
    }
}
