import java.io.*;
import java.util.*;

public class Model {
    private Vector<Item> itemList;
    private Vector<Item> selectedList;
    private static View view;

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
        if (view != null) {
            view.refresh();
        }
    }

    public void save(String fileName) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retrieve(String fileName) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            itemList = (Vector<Item>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setView(View viewInstance) {
        view = viewInstance;
    }
}
