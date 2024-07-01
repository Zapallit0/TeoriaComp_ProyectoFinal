package Main;

import Items.ItemKey;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ItemKey();
        gp.obj[0].worldX = 23 * gp.tilesSize;
        gp.obj[0].worldY = 7 * gp.tilesSize;
    }

}
