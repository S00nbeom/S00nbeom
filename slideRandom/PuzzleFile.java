package teamProject.slideRandom;

import javax.swing.*;
import java.io.*;

public class PuzzleFile {
    private int now_score = 0;
    private int high_score = 0;
    public PuzzleFile(int ys) {
        now_score = ys; //일단 0을 받아와서 초기화
    }
    public int r_score(){
        try {
            FileReader reader = new FileReader("high_score.txt");
            BufferedReader infile = new BufferedReader(reader);
            String line = infile.readLine();
            high_score = Integer.parseInt(line);
            return high_score;
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,"파일에 문제가 발생했습니다. 파일 생성을 시도합니다");
            try {
                FileWriter writer = new FileWriter("high_score.txt");
                PrintWriter outfile = new PrintWriter(writer);
                outfile.println("0");
                outfile.close();
            }
            catch (IOException e1) {
                JOptionPane.showMessageDialog(null,"파일을 생성할 수 없습니다. 수동으로 생성하시오. 프로그램을 종료합니다.");
                System.exit(0);
            }
            r_score();
        }
        return high_score;
    }
    public void w_score(int ys) {
        now_score = ys; //게임이 끝나고 생성된 점수로 변경
        if (now_score > high_score) {
            try {
                FileWriter writer = new FileWriter("high_score.txt");
                PrintWriter outfile = new PrintWriter(writer);
                outfile.println(now_score);
                outfile.close();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "파일 쓰기에 문제가 생겼습니다. 프로그램을 종료합니다.");
                System.exit(0);
            }
        }
    }
}
