import java.text.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;

public class Model {
    private Vector itemList; // List of all items (triangles, lines, etc.)
    private Vector selectedList; // List of selected items
    private static View view;

    public Model() {
        itemList = new Vector();
        selectedList = new Vector();
    }

    public static void setView(View view) {
        Model.view = view;
    }

    public void markSelected(Item item) {
        // Marks an item as selected by moving it to the selected list.
        if (itemList.contains(item)) {
            itemList.remove(item);
            selectedList.add(item);
            view.refresh();
        }
    }

    public void unSelect(Item item) {
        if (selectedList.contains(item)) {
            selectedList.remove(item);
            itemList.add(item);
            view.refresh();
        }
    }

    public void deleteSelectedItems() {
        selectedList.removeAllElements();
        view.refresh();
    }

    public void addItem(Item item) {
        // Adds a new item (triangle, line, etc.) to the list and refreshes the view.
        itemList.add(item);
        view.refresh();
    }

    public void removeItem(Item item) {
        // Removes an item from the list and refreshes the view.
        itemList.remove(item);
        view.refresh();
    }

    public Enumeration getItems() {
        return itemList.elements();
    }

    public void setChanged() {
        view.refresh();
    }

    public Enumeration getSelectedItems() {
        return selectedList.elements();
    }

    // Saves the state of the drawing to a file
    public void save(String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(itemList);
            output.writeObject(selectedList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // Retrieves the state of the drawing from a file
    public void retrieve(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            itemList = (Vector) input.readObject();
            selectedList = (Vector) input.readObject();
            view.refresh();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    // --- New Methods for Undo/Redo Support ---

    public void undoItem(Item item) {
        // Removes the item (used for undo operation)
        if (itemList.contains(item)) {
            itemList.remove(item);
            view.refresh();
        }
    }

    public void redoItem(Item item) {
        // Re-adds the item (used for redo operation)
        if (!itemList.contains(item)) {
            itemList.add(item);
            view.refresh();
        }
    }
}
