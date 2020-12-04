
package cat.proven.store.views;

import cat.proven.menu.Menu;
import cat.proven.store.controllers.MainController;
import cat.proven.store.model.Model;
import cat.proven.store.model.Product;
import cat.proven.util.InOut;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Main view of store application
 * @author Chems
 */
public class MainView {

    private final MainController controller;
    private final Model model;
    private final Menu menu;
    
    private boolean exit;

    public MainView(MainController controller, Model model) {
       this.controller = controller;
       this.model=model;
       this.menu=new MainMenu();
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    
    public void display(){
        
        exit=false;
        
        do{
            menu.show();
            String action = menu.getSelectedOptionActionCommand();
            processAction(action);
            
            
        }while(!exit);
        
        
    }

    /**
     * Process an action input from user
     * @param action action form user
     */
    public void processAction(String action) {
        if(action !=null){
            switch(action){            
                default:
                    controller.processRequest(action);
                    
            }    
        }
    }
    
    
    
    /**
     * displays data to user.
     * @param data to display
     */
    
    public void displayProductList(List<Product> data){
        if(data != null){
            for(int i=0; i<data.size();i++){
            System.out.println(data.get(i).toString());
        }
            System.out.format("%d products found\n", data.size());
            
        }else{
            System.out.println("Error, products Not found, NULL");
        }

    }
    
    
    /**
     * displays a product to use
     * @param product product to display
     * In case of error show message to user
     */
    public void displayProduct(Product product){
    
        if(product != null){
            System.out.println(product.toString());
        }else{
            System.out.println("Product not found");
        }
        
    }
    
    
    /**
     * display a message and ards an answe from the user.
     * @param message the message to display
     * @return the answer from the user or null in case of error
     */
        
    public String inputString(String message){
        
        System.out.println(message);
        //Scanner sc=new Scanner(System.in);
        Scanner sc = InOut.getScanner();
        return sc.next();
        
        
    }
    
    /**
     * Display a message to user
     * @param message to display
     */
    public void displayMessage(String message){
        System.out.println(message);
    }
  
}
