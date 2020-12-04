package cat.proven.store;

import cat.proven.store.controllers.MainController;
import cat.proven.store.model.Model;
import cat.proven.store.model.persist.DbConnect;

/**
 *Main MVC class
 * @author Chems
 */
public class MainMvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainMvc app= new MainMvc();
        app.run();
        
        
       
    }
    
    /**
     * initialize model and controller to start program
     */
    private void run(){
        try {
            DbConnect.loadDriver();
            Model model = new Model();
            MainController controller = new MainController(model);
            controller.start();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(MainMvc.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading database driver, contact you administrator!");
        }
        
    }
    
}
