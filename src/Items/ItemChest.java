package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemChest extends SuperObject{
    public ItemChest(){
        name="Chest";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/Tiles/items/chest.png"));
        }catch (IOException e){
        }
        collision=true;
    }
}
