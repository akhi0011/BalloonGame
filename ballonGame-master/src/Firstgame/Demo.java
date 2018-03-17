package Firstgame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

/**
 * Created by hunter brothers on 8/29/2016.
 */
public class Demo implements MouseListener {
    public static int ballonyvelocity;
    public static int ballonycord;
    public static int bombycord;
    public static Rectangle ballon1rect;
    public static int ballonxcord;
    public static int bombxcord;
    public static Rectangle bomb1rect;
    public static java.applet.AudioClip hitaudio=null;
    public static MouseEvent mouse1;
    public  static int count=0;
   public static Boolean showballon=true;
   public static Boolean showbomb=true;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 400));
        panel.setRequestFocusEnabled(true);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);


        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        panel.requestFocus();
        panel.addMouseListener(new Demo());
        Image ballon = null;
        Image bomb = null;
        Image gameover = null;
        try {
            ballon = ImageIO.read(Demo.class.getResource("images/ballon1.png"));
            bomb = ImageIO.read(Demo.class.getResource("images/bomb2.png"));
            gameover = ImageIO.read(Demo.class.getResource("images/gameover.png"));
hitaudio=Applet.newAudioClip(Demo.class.getResource("audio/hit.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        ballonyvelocity = -10;
        ballonycord = 400;
        bombycord=400;
         ballon1rect = new Rectangle();
        bomb1rect = new Rectangle();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(ballonycord>=400 ||ballonycord<=-128) {
                int randomxcordballon = random.nextInt(600 - 100) + 100;
                int randomycordballon = random.nextInt(500 - 280) + 280;
                     ballonycord=randomycordballon;
                     ballonxcord=randomxcordballon;
                     showballon=true;
                     count=count+1;
                }
            if(bombycord>=400||bombycord<=-128) {
                int randomycordbomb = random.nextInt(600 - 280) + 280;
                int randomxcordbomb = random.nextInt(600 - 100) + 100;
                bombxcord = randomxcordbomb;
                bombycord = randomycordbomb;
            }

            ballonycord = ballonycord + ballonyvelocity;
            bombycord = bombycord - 5;
            Graphics graphics = panel.getGraphics();
            if(count==4||showbomb==false){
              graphics.drawImage(gameover,100,100,null);
              break;
            }
            graphics.clearRect(0, 0, 700, 400);
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(0, 0, 700, 400);
            if(showballon&&count<=3) {
                graphics.drawImage(ballon, ballonxcord, ballonycord, null);
            }
            if(showbomb) {
                graphics.drawImage(bomb, bombxcord, bombycord, null);
            }
           ballon1rect.setBounds(ballonxcord,ballonycord,128,128);
                bomb1rect.setBounds(bombxcord,bombycord,128,128);
               graphics.dispose();
            }
        }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(ballon1rect.contains(e.getX(),e.getY())) {
           hitaudio.play();
           ballonyvelocity=ballonyvelocity-3;
           showballon=false;
           ballonycord=400;
           count=0;
        }
            if(bomb1rect.contains(e.getX(),e.getY())){
              hitaudio.play();
              gameover();
            }
        }
    private void gameover() {
        showballon = false;
        showbomb = false;
        count=4;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}