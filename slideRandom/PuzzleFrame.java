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
    private int cnt = 0;
    private JLabel label = new JLabel("time: 0s, move: 0");
    private JLabel score = new JLabel("Your score : 0,");
    private int your_score = 0;
    private PuzzleFile pf = new PuzzleFile(your_score);
    private int high_score = pf.r_score();
    private JLabel hs = new JLabel("High score : " + high_score);
    private int nowTime = 0;

    public PuzzleFrame(SlidePuzzleBoard b) {
        board = b;
        size = board.getSize();
        button_board = new PuzzleButton[size][size];
        high_score = pf.r_score();
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
        JPanel p3 = new JPanel(new FlowLayout());
        p3.add(score);
        p3.add(hs);
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);

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
                            button_board[r][c].setBackground(new Color(255, 250, 51));
                        else button_board[r][c].setBackground(Color.white);
                    }
                    else {
                        button_board[r][c].setText("");
                        button_board[r][c].setBackground(new Color(0, 0, 0));
                    }
                }
            }
            if (board.gameOver())
                finish();
        }
    }
    public void finish(){
        for (int r = 0; r <size; r++) {
            for (int c = 0; c < size; c++) {
                if (r != size - 1 || c != size - 1)
                    button_board[r][c].setBackground(Color.white);
                else {
                    button_board[size-1][size-1].setText("Done");
                    button_board[size-1][size-1].setForeground(Color.white);
                }
            }
        }
        finished = true;
        started = false;
    }
    public void started() {
        started = true;
        cnt = 0;
        button_board[size-1][size-1].setForeground(Color.BLACK);
        score.setText("Your score : " + 0);
    }
    public void time(){
        while(true){
            updateTime(LocalTime.now());
        }
    }
    public void updateTime(LocalTime t) {
        nowTime = t.getHour()*3600+t.getMinute() * 60 + t.getSecond() - sb.getT();
        if(started && !finished)
            label.setText("time: "+nowTime + "s, move: " + cnt);
        else if (finished){
            your_score = (int)(500 -(nowTime * 1.5 + cnt * 0.3));
            if (your_score <= 0){
                your_score = 0;
            }
            score.setText("Your score : " + your_score+",");
            pf.w_score(your_score);
            high_score = pf.r_score();
            hs.setText("High score : " + high_score);
            finished = false;
        }
    }

    public void updateCnt(){
        cnt++;
        if(started && !finished) {
            label.setText("time: " + nowTime + "s, move: " + cnt);
            your_score = (int)(500 -(nowTime * 1.5 + cnt * 0.3));
            if (your_score <= 0) your_score = 0;
            score.setText("Your score : " + your_score+",");
        }
    }
}
