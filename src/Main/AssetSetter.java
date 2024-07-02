package Main;

import Items.ItemChest;
import Items.ItemDoor;
import Items.ItemKey;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ItemKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;
        gp.obj[1] = new ItemDoor();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;
        gp.obj[2] = new ItemChest();
        gp.obj[2].worldX = 23 * gp.tileSize;
        gp.obj[2].worldY = 5 * gp.tileSize;
    }


}
