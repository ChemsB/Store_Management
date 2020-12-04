package cat.proven.store.controllers;

import cat.proven.store.model.Model;
import cat.proven.store.model.Product;
import cat.proven.store.views.FormInterface;
import cat.proven.store.views.FridgeForm;
import cat.proven.store.views.MainView;
import cat.proven.store.views.ProductForm;
import cat.proven.store.views.TvForm;
import cat.proven.util.InOut;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *Main controller of Store Aplication
 * @author Chems
 */
public class MainController {

    private final Model model;
    private final MainView view;

    public MainController(Model model) {
        this.model=model;
        this.view= new MainView(this, model);
    }
    
    /**
     *Show the menu and process the user's option
     */
    public void start(){
        
        view.display();
        
    }


    public void processRequest(String action) {
    
        if(action != null){
            switch (action){
      
                case "exit"://Exit
                    view.setExit(true);
                    view.displayMessage("Goodbye!");
                    break;

                case "list_all":// list all products

                    listAllProduct();

                    break;

                case "list_product_by_code"://Find product with user code introduced

                    findProductByCode();

                    break;

                case "list_with_stock"://Find product with less user stock introduced

                   findProductWithLowStock();

                    break;

                case "add_product": //Add new product

                   addData();
                    break;

                case "modify_product"://Modify one Product
                    modifyProduct();

                    break;

                case "remove_product":
                  removeProduct();
                    break;

                default:
                    view.displayMessage("Invalid Option!");
                    break;

            }
            view.displayMessage("Processing action: "+action);
            
        }
        
    }

        

    /**
     * retrieves the list of all products and displays all products.
     */
    private void listAllProduct(){
        // retrive the list of products
        List <Product> result=model.listAllProducts();
        //display the retrived data
        view.displayProductList(result);

    }
    

    /**
     * Asks the user to input the code of the product to search
     * if input is successful searches the product with the given code
     * if not, reports that to user.
     * If product found, shows product to user, if not reports error to user
     */
    private void findProductByCode(){
        
        String code = view.inputString("Input The Code: ");
        if(code==null){
            view.displayMessage("Error reading the code");
        }else{
            //Seacrh a product by code
            Product result=model.findProductByCode(code);
            view.displayProduct(result);
        }
        
    }

    
    
    /**
     * asks the user to input the values minimum stock
     * if error reading stock, report that to user
     * if correcty read, search products with stock lower than the given one,
     * and report resudisplayProductListlt to user
     */
    
    private void findProductWithLowStock() {
        
        int lessThan;
        List<Product> result=new ArrayList();
        try{
            lessThan=Integer.parseInt(view.inputString("Enter the thresold number to stock"));
            result=model.findProductWithLowStock(lessThan);
            view.displayProductList(result);
        }catch(NumberFormatException e){
            view.displayMessage("Incorrect Format");
        }
       
        

    }

    
     /**
     * Input data for a product to add
     * @return product to add
     * In case of error show message to user
     */
    
    private void addData() {
        
        
            //Input type from user
            FormInterface form = new ProductForm();
            

            //Input data depending the type of old product
            Product prod = form.input();
        
            if(prod!=null){
                //In this case add new porduct Tv or Fridge
                boolean res=model.addProduct(prod);

            if(res==true){           
 
                view.displayMessage("product successfully added");
            }else{
                view.displayMessage("Product not added");
            } 
        }
        

    }

    /**
     * asks the user code of the product to modify
     * if correctly read, searches the product in the data source
     * if not found, reports that to user. if found, shows the porduct to user
     * and asks for confirChemson.
     * If confirmed, reads from the new data for the porduct
     * if not read, report to userm if read, modify the product and report result to user
     */
 
    
    private void modifyProduct() {
        
        boolean res=false;
        int confirm;
        Product oldProduct;
        Product newProduct=null;
        String code;
        Class c;
        String type="";
        Scanner sc= InOut.getScanner();
        FormInterface form = null;
        
        //Input code to search product
        view.displayMessage("Enter code: ");
        code=sc.next();
        
        //Search product with that code
        oldProduct=model.findProductByCode(code);

        view.displayProduct(oldProduct);
        

        try{
        //Get type of porduct with instanceof, class or class

            if(oldProduct!=null){
                
                view.displayProduct(oldProduct);

                form = new ProductForm();
   
                //Cretae new porduct with appropiate type TV or FRIDGE
                newProduct=form.input();


                if(newProduct!=null){
                    //Show new Product
                    view.displayProduct(newProduct);
                    System.out.println("Are you sure? 1-Yes 2-No");
                    confirm = sc.nextInt();
                    if(confirm==1){
                        res=model.modifyProduct(oldProduct, newProduct);
                    }
                }
            }else{
                res=false;
            }
            
            
        }catch(NullPointerException e){
            res=false;                
            sc.next();
        }catch(InputMismatchException e){
            res=false;
            sc.next();
        }

            //If recives true, modified product
            if(res==true){
                view.displayMessage("Product modified");
            }else{
                view.displayMessage("Product not modified");
            }
    }
    
    
    
    /**
     * asks the user code of the product to remove
     * if correctly read, searches the product in the data source
     * if not found, reports that to user. if found, shows the porduct to user
     * 
     */
    private void removeProduct() {
        
        
        int confirm=0;
        String code;
        Product prodToRemove;
        boolean res=false;
        
        code = view.inputString("Input The Code: ");
        if(code==null){
            view.displayMessage("Error reading the code");
        }
        
        try{
           prodToRemove=model.findProductByCode(code);
            view.displayProduct(prodToRemove);

            if(prodToRemove != null){
               confirm= Integer.parseInt(view.inputString("Are you sure? 1-Yes 2-No"));
            }

           if(confirm==1){
               res=model.removeProduct(prodToRemove);

           }
       }catch(InputMismatchException e){
           res=false;
       }
       
       if(res==true){
           view.displayMessage("Product Removed");
       }else{
           view.displayMessage("Product not Removed");
       }
    }

}
