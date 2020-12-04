package cat.proven.store.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import cat.proven.store.model.Product;
import cat.proven.store.model.Store;
import cat.proven.store.views.FormInterface;
import cat.proven.store.views.FridgeForm;
import cat.proven.store.views.ProductForm;
import cat.proven.store.views.TvForm;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Chems
 */
public class Store {
    

    private List<Product> products;

    
    public Store() {
        this.products= new ArrayList();
    }
    
    
    //accessors

    /**
     * 
     * @return number of products 
     */
    public int getNumProducts() {
        return products.size();
    }
    
    

    /**
     * recives all products from the data store
     * @return list of all products or null in case of error
     */ 
    public List<Product> listAllProducts(){
        return products;
    }
    
    
    /**
     * retrieves the element at position index
     * @param index position of the element to retrieve
     * @return element at the given position or null in came of error
     */
    public Product get(int index){
        
        Product p=null;

        if((index >=0) && (index < getNumProducts())){
            p=products.get(index);
        }
        
        return p;
        
    }
    
    /**
     * recives product with the given code from the data source
     * @param code the code to search
     * @return product with the given code or null if not found or in case of error
     */
    public Product findProductByCode(String code) {
        

       Product result=null;
       Iterator<Product> itr = products.iterator();
       Product product;
       
       for(Product prod: products){
           
           product=itr.next();
           if(product.getCode().equals(code)){
               result=product;
           }
           
           
       } 
       return result;
    }
    
 
    /**
     * adds a product to the data source
     * avoids adding null products or products with null code
     * else avoids adding when list is full and when another product with the same
     * code exists in the list
     * @param product the product to add
     * @return true if successfully added, false otherwise
     */
    
    public boolean addProduct(Product product){

        boolean b=false;
        //Check error condition.
        if(findProductByCode(product.getCode()) != null){
            b=false;
        }else if(product.getCode().equals("")){
            b=false;
        }else{
            products.add(product);
            b=true;
        }
        return b;
    }
    
    /**
     * Find products with stock lower than the given value
     * @param stock the threshold value to search products
     * @return list of porducts lower than stock or null in case of error
     */
    
    public List<Product> findProductWithLowStock(int stock){
        
        
        List <Product> listLower=new ArrayList<>(); //create other list
        Iterator<Product> itr = products.iterator();
        Product product;
        
        for(Product prod: products){
           product=itr.next();
                if(product.getStock()<stock){
                    listLower.add(prod);
                }
        }
        return listLower; //Return new list
    }
    
    
    
    /**
     * loads initial test data into data sources
     */
    /*public void loadTestData(){
        
        addProduct(new Tv("C01","desc03",12546.1,60,50));
        addProduct(new Fridge("C02","desc04",473.1,37,50,true));
        addProduct(new Fridge("C03","desc06",444.1,1,70,false));
        addProduct(new Tv("C04","desc04",789.1,60,50));
        addProduct(new Tv("C05","desc05",222.1,45,70));
    }*/
    
    
    /**
     * Update data from a product
     * @param oldProduct product to update
     * @param newProduct data form a new porduct
     * @return true if success modified or false in case of error o otherwise
     * check if the new code exists in other porduct return false
     * 
     */
    public boolean modifyProduct(Product oldProduct, Product newProduct){
        
        boolean res=true;
        int pos;
        Product product;
        //Find the position of the porduct to modify
        pos=products.indexOf(oldProduct);
        Iterator<Product> itr = products.iterator();
        
       for(Product prod: products){
           
           product=itr.next();
           
           if(products.indexOf(product)!=pos){
               if(product.getCode().equals(newProduct.getCode())){
                   res=false;
               }
               
           }
        }
        if(res==true){
            //Modify porduct in the 
            products.set(pos, newProduct);
        }
    return res;
        
    }

}
