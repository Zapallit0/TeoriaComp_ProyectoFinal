package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    int speed,life=10,maxLife=10,dmg;
    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize /2);
        screenY = gp.screenHeight/2 - (gp.tileSize /2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/goingUp2.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/goingUp1.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/goingDown1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/goingDown2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/standingLeft.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/walkingLeft.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/standingRight.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/walkingRight.png")));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() throws IOException {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex=gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        gp.ui.showMessage("Arriba");
                        worldY -= speed;
                        break;
                    case "down":
                        gp.ui.showMessage("Abajo");
                        worldY += speed;
                        break;
                    case "left":
                        gp.ui.showMessage("Izquierda");
                        worldX -= speed;
                        break;
                    case "right":
                        gp.ui.showMessage("Derecha");
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) throws IOException {
        if(i!=999){
            String objectName=gp.obj[i].name;
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i]=null;
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.obj[i]=null;
                        hasKey--;
                    }
                    break;
                case "Chest":
                    if(hasKey>0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    else{
                        gp.ui.showMessage("No tienes llaves");
                    }
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    public void lessLife(int nlife){
            nlife=life-nlife;
    }
    public void moreLife(int nlife){
            nlife=life+nlife;
    }
    public int getMaxLife(){
        return maxLife;
    }

}
