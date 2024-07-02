package Main;

import Items.ItemKey;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

public class UI extends JPanel {
    GamePanel gp;
    Graphics2D g2;
    Font fontOPTitles =new Font("ONE PIECE",Font.PLAIN,120);
    Font fontOPSmall=new Font("ONE PIECE", Font.PLAIN,50);
    Font fontOPMedium=new Font("ONE PIECE",Font.PLAIN,60);
    ItemKey key=new ItemKey();
    BufferedImage keyImg;

    public int commandNum =0;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    float transparency = 0.8f;
    float transparencyText = 1.0f;
    int iconHeight=30;
    int iconWidth =30;


    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp=gp;
        keyImg=key.image;
    }

    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        drawPlayState(g2);
        g2.setFont(fontOPTitles);
        g2.setColor(Color.white);
    }
    public void drawPlayState(Graphics2D g2){
        commandNum=0;
        drawRecStats(g2);
        g2.setFont(fontOPSmall);
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencyText));
        g2.drawString("  ="+gp.player.hasKey,50,100);
        g2.drawImage(keyImg,30,70, iconWidth,iconHeight,null);
        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(32F));
            g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
            messageCounter++;
            if(messageCounter>120){
                messageCounter=0;
                messageOn=false;
            }
        }
    }
    public void drawRecStats(Graphics2D g){
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        int x = 10;
        int y = 60;
        int width = 150;
        int height = 150;
        int arcWidth = 20;
        int arcHeight = 20;
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight);
        g.setColor(Color.GRAY);
        g.fill(roundedRect);
    }

}
