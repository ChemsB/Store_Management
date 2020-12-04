
package cat.proven.store.views;

import cat.proven.store.model.Fridge;
import cat.proven.store.model.Product;
import cat.proven.util.InOut;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chems
 */
public class FridgeForm implements FormInterface{
    
    /**
     * Input data from user form a new Fridge
     * @return in this case a new Fridge or null in case of exception
     */
    @Override
    public Product input() {

        Scanner sc = InOut.getScanner();
        Fridge p = null;
        
        String code;
        String desc;
        double price;
        int stock;
        int capacity;
        boolean frost;
        
        System.out.println("Introduce CODE");
        code=sc.next();
        
        System.out.println("Enter description");
        desc=sc.next();

            //Check excepcions
            try{
                System.out.println("Enter price");
                price=sc.nextDouble();
                System.out.println("Enter stock");
                stock=sc.nextInt();
                System.out.println("Enter capacity");
                capacity=sc.nextInt();
                System.out.println("Enter Frost");
                frost=sc.nextBoolean();
                
                //Check negative values
                if(price >=0 && stock>=0 && capacity >=0){
                    p= new Fridge(code,desc,price,stock,capacity,frost);
                }else{
                    System.out.println("Values can't be negative");
                }
            }catch(InputMismatchException e){
                p=null;
                System.out.println("Error: ");
                sc.next();
            }
            return p;
        
    }
    
}
