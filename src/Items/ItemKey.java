package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemKey extends SuperObject {

    public ItemKey() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Tiles/items/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
