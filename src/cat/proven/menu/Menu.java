package cat.proven.menu;

import cat.proven.util.InOut;
import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Encapsulates the creation and use of a generic menu
 * @author Chems
 */
public class Menu {

    /**
     * title of menu
     */
    protected String title;
    /**
     * list of options
     */
    protected List<MenuItem> options;

    /**
     * Menu constructor
     * @param title the menu title.
     */
    public Menu(String title) {
        this.title = title;
        this.options = new ArrayList<>();
    }

    /**
     * Menu constructor
     */
    public Menu() {
        this.title = null;
        this.options = new ArrayList<>();
    }

    /**
     * gets the menu title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the menu title
     * @param title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the option with the specified index
     *
     * @param index: the index of the option to get
     * @return the option is the specified position of the list
     */
    public MenuItem getOption(int index) {
        return options.get(index);
    }

    /*======Methods=======*/
    /**
     * @param option: the option to be added
     * @return true if option has been added, false otherwise
     */
    public boolean addOption(MenuItem option) {
        return options.add(option);
    }

    /**
     * removes an option from the list
     *
     * @param option: the option to be removed
     * @return true if option has been removed, false otherwise
     */
    public boolean removeOption(MenuItem option) {
        return options.remove(option);
    }

    /**
     * removes an option (given its position) from the list
     * @param index: the position of the option in the list
     * @return the option removed
     */
    public MenuItem removeOption(int index) {
        return options.remove(index);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu [title=");
        builder.append(title);
        builder.append(", options=");
        builder.append(options);
        builder.append("]");
        return builder.toString();
    }

    /**
     * shows the menu
     */
    public void show() {
        System.out.format("============%s============\n", title);
        ListIterator<MenuItem> it = options.listIterator();
        int idOption = 0;
        while (it.hasNext()) {
            System.out.format("[%d] %s\n", idOption, it.next().getText());
            idOption++;
        }
    }

    /**
     * gets input from user to select an option
     * @return a value identifying the selected option or -1 in case of error or
     * wrong option.
     */
    public int getSelectedOptionIndex() {
        Scanner sc = InOut.getScanner();
        int opt = -1;
        try {
            System.out.print("Select an option: ");
            opt = sc.nextInt();
            if ((opt < 0) || (opt >= options.size())) {
                opt = -1;
            }
        } catch (InputMismatchException ime) {
            opt = -1;
            sc.next();
        }
        return opt;
    }

    /**
     * gets the action command related to the selected option.
     * @return action command of selected option
     */
    public String getSelectedOptionActionCommand() {
        int optionNumber = getSelectedOptionIndex();
        String actionCommand = null;
        if ((optionNumber >= 0) && (optionNumber < options.size())) {
            actionCommand = options.get(optionNumber).getActionCommand();
        }
        return actionCommand;
    }
}
