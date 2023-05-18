import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1l;
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH /12*9;
    public static final int SCALE = 3;
    public static final String NAME = "The Game";
    private JFrame frame;
//    public JFrame
    public boolean running = false;
    public Game(){
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this.BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    @Override
    public void run() {

    }
    //they used public symchronised void start()
    private void start() {
        new Thread(this).start();
    }

    private void stop(){

    }

    public static void main(String[] args) {
        new Game().start();
    }



}