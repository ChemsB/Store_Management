package cat.proven.store.views;

import cat.proven.store.model.Product;
import cat.proven.util.InOut;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chems
 */
public class ProductForm implements FormInterface {

    /**
     * Input data from user form a new Product
     *
     * @return a new Product
     */
    @Override
    public Product input() {

        Scanner sc = InOut.getScanner();

        Product p = null;

        String code;
        String desc;
        double price;
        int stock;

        System.out.println("Introduce CODE");
        code = sc.next();

        System.out.println("Enter description");
        desc = sc.next();

        //Check exception
        try {
            
            System.out.println("Enter price");
            price = sc.nextDouble();
            System.out.println("Enter stock");
            stock = sc.nextInt();

            p = new Product(code,desc,price,stock);

        }catch(InputMismatchException e) {
            
            p=null;
            System.out.println("Error Value");
            sc.next();
            
        }
        
        return p;

    }
}
