
package cat.proven.store.views;

import cat.proven.menu.Menu;
import cat.proven.menu.MenuItem;

/**
 * Main Menu for porduct store aplication
 * @author Chems
 */
public class MainMenu extends Menu{

    
    
    public MainMenu() {
        
        setTitle("Store main menu");
        
        addOption(new MenuItem("Exit","exit"));
        addOption(new MenuItem("List all Products","list_all"));
        addOption(new MenuItem("List product by code ","list_product_by_code"));
        addOption(new MenuItem("List products with low stock","list_with_stock"));
        addOption(new MenuItem("Add new product","add_product"));
        addOption(new MenuItem("Modify a product","modify_product"));
        addOption(new MenuItem("Remove a product","remove_product"));
        

        
    }
    

    
    
    
    
}
