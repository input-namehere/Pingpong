import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1l;
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH /12*9;
    public static final int SCALE = 3;
    public static final String NAME = "The Game";
    private JFrame frame;
//    public JFrame
    public boolean running = false;
    private int tickCount = 0;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(){
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this,BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        int frames =0;
        int ticks = 0;
        long lastTimer = System.currentTimeMillis();
        double delta =0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while (delta>= 1) {
                ticks ++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (shouldRender) {
                frames++;
                render();
            }
            //System.out.println(frames+","+ticks);
            if (System.currentTimeMillis()-lastTimer>=1000){
                lastTimer+=1000;
                System.out.println(ticks +" ticks " + frames+ " frames");
                frames= 0;
                ticks =0;
            }
        }
    }
    //they used public symchronised void start()
    private void start() {
        running = true;
        new Thread(this).start();
    }

    private void stop(){
        running = false;
    }
    public void tick() {
        tickCount ++;
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = i +tickCount;

        }
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game().start();
    }



}