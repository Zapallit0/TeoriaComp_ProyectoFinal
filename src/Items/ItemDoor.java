package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemDoor extends SuperObject{
    public ItemDoor(){
        name="Door";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/Tiles/items/door.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
