
package cat.proven.store.views;

import cat.proven.store.model.Product;
import cat.proven.store.model.Tv;
import cat.proven.util.InOut;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chems
 */
public class TvForm implements FormInterface{

    /**
     * Input data from user form a new TV
     * @return In this case a new TV or null in case of error
     */
    @Override
    public Product input() {

        Scanner sc = InOut.getScanner();
        Tv p = null;
        
        String code;
        String desc;
        double price;
        int stock;
        
        System.out.println("Introduce CODE");
        code=sc.next();
        
        System.out.println("Enter description");
        desc=sc.next();

            //Check exception
            try{
                System.out.println("Enter price");
                price=sc.nextDouble();
                System.out.println("Enter stock");
                stock=sc.nextInt();
                System.out.println("Inches: ");
                int inches=sc.nextInt();

                //Check negative values
                if(price >=0 && stock>=0 && inches >=0){
                    p= new Tv(code,desc,price,stock,inches);
                }else{
                    System.out.println("Values can't be negative");
                }
                
            }catch(InputMismatchException e){
                p=null;
                System.out.println("Error Value");
                sc.next();
            }
            return p;
    }
}
