import java.util.Enumeration;
import java.util.Vector;

public class Model {
    private Vector<Item> itemList;
    private Vector<Item> selectedList;

    public Model() {
        itemList = new Vector<>();
        selectedList = new Vector<>();
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

    public Enumeration<Item> getSelectedItems() {
        return selectedList.elements();
    }

    public void markSelected(Item item) {
        if (itemList.contains(item)) {
            itemList.remove(item);
            selectedList.add(item);
        }
    }

    public void unSelect(Item item) {
        if (selectedList.contains(item)) {
            selectedList.remove(item);
            itemList.add(item);
        }
    }

    public void deleteSelectedItems() {
        selectedList.clear();
    }

    public void setChanged() {
        // Notify the view to refresh.
        View.getInstance().refresh();
    }
}
