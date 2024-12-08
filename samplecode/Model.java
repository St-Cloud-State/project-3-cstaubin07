import java.util.*;
import java.awt.*;

public class Model {
    private Vector<Item> itemList;

    public Model() {
        itemList = new Vector<>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public Enumeration<Item> getItems() {
        return itemList.elements();
    }
}
